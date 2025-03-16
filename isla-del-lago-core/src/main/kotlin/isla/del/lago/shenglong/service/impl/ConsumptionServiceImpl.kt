package isla.del.lago.shenglong.service.impl

import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.exception.ErrorInfo
import isla.del.lago.shenglong.exception.IdlException
import isla.del.lago.shenglong.extensions.objectToJson
import isla.del.lago.shenglong.mapper.ConsumptionMapper
import isla.del.lago.shenglong.model.Bill
import isla.del.lago.shenglong.model.Consumption
import isla.del.lago.shenglong.repository.ConsumptionRepository
import isla.del.lago.shenglong.request.consumption.ConsumptionInfo
import isla.del.lago.shenglong.request.consumption.CreateConsumptionsRequest
import isla.del.lago.shenglong.response.consumption.ConsumptionByApartmentResponse
import isla.del.lago.shenglong.response.consumption.ConsumptionDetailByApartmentResponse
import isla.del.lago.shenglong.response.consumption.ConsumptionDetailResponse
import isla.del.lago.shenglong.response.consumption.ConsumptionResponse
import isla.del.lago.shenglong.service.BillService
import isla.del.lago.shenglong.service.ConsumptionService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConsumptionServiceImpl(
    private var consumptionRepository: ConsumptionRepository,
    private var billService: BillService
) : ConsumptionService {

    companion object {
        private val logger = LoggerFactory.getLogger(ConsumptionServiceImpl::class.java)
    }

    override fun addConsumptions(billId: Int, userId: String, createConsumptionsRequest: CreateConsumptionsRequest) {
        val traceabilityId = UUID.randomUUID().toString()

        logger.info(
            "--ConsumptionService:AddConsumptions --TraceabilityId:[{}] --UserId:[{}] --Request:[{}]",
            traceabilityId, userId, createConsumptionsRequest.objectToJson()
        )

        if (Constant.Consumption.MAX_CONSUMPTION_SIZE == consumptionRepository.findAllByBillId(billId).size) {
            throw ErrorInfo.ERROR_CONSUMPTIONS_EXISTS_FOR_BILL_ID.buildIdlException()
        }

        saveConsumptionsOnDatabase(billId, createConsumptionsRequest, traceabilityId, userId)

        if (Constant.Consumption.MAX_CONSUMPTION_SIZE != consumptionRepository.findAllByBillId(billId).size) {
            consumptionRepository.deleteAllByBillId(billId)
            throw ErrorInfo.ERROR_CONSUMPTIONS_NOT_CREATED.buildIdlException()
        }
    }

    override fun getConsumptionDetails(billId: Int, userId: String, apartmentId: String): ConsumptionDetailResponse {
        logger.info(
            "--ConsumptionService:GetConsumptionDetails --UserId:[{}] --BillId:[{}] --ApartmentId:[{}]",
            userId, billId, apartmentId
        )

        val bill = billService.getBillById(billId)

        if (Constant.TOTAL == apartmentId) {
            val consumptionDetailResponse = ConsumptionDetailResponse()

            Constant.Apartment.ALL.forEach {
                val consumption = getConsumptionByBillIdAndApartmentId(billId, it)
                val mappedConsumption = ConsumptionMapper.mapToConsumptionDetailResponse(bill, consumption, true)

                consumptionDetailResponse.updateFromPreviousResponse(mappedConsumption)
            }

            return consumptionDetailResponse
        }

        val consumption = getConsumptionByBillIdAndApartmentId(billId, apartmentId)

        return ConsumptionMapper.mapToConsumptionDetailResponse(bill, consumption)
    }

    override fun getConsumptionDetailsByApartmentId(
        userId: String,
        apartmentId: String
    ): List<ConsumptionDetailByApartmentResponse> {
        logger.info(
            "--ConsumptionService:GetConsumptionDetailsByApartmentId --UserId:[{}] --ApartmentId:[{}]",
            userId, apartmentId
        )

        return billService.getAllBills(userId).map {
            ConsumptionDetailByApartmentResponse().apply {
                billId = it.billId
                startDate = it.startDate
                endDate = it.endDate
                consumptionDetail = getConsumptionDetails(it.billId!!, userId, apartmentId)
            }
        }
    }

    override fun getConsumptionsFromPreviousBill(userId: String, billId: Int): List<ConsumptionByApartmentResponse> {
        logger.info(
            "--ConsumptionService:GetConsumptionsFromPreviousBill --UserId:[{}] --BillId:[{}]",
            userId, billId
        )

        try {
            val bill = billService.getBillById(billId)
            val previousBill = billService.getPreviousBill(bill.startDate!!)

            val response = mutableListOf<ConsumptionByApartmentResponse>()

            consumptionRepository.findAllByBillId(previousBill.billId!!).forEach {
                val consumptionByApartment = ConsumptionByApartmentResponse().apply {
                    apartmentId = it.apartmentId
                    consumption = ConsumptionResponse().apply {
                        value = it.value
                    }
                }

                response.add(consumptionByApartment)
            }

            return response
        } catch (ex: Exception) {
            logger.warn(
                "--ConsumptionService:GetConsumptionsFromPreviousBill --UserId:[{}] --BillId:[{}] --ExceptionMessage[{}]",
                userId, billId, ex.message
            )

            return listOf()
        }
    }

    fun saveConsumptionsOnDatabase(
        billId: Int,
        createConsumptionsRequest: CreateConsumptionsRequest,
        traceabilityId: String,
        userId: String
    ) {
        val currentBill = billService.getBillById(billId)
        val calculatedConsumptions = calculateConsumptions(currentBill, createConsumptionsRequest.consumptions!!)
        val totalConsumed = calculatedConsumptions.values.sum()

        createConsumptionsRequest.consumptions?.forEach { consumption ->
            val percentageConsumed = calculatedConsumptions[consumption.apartmentId]?.div(totalConsumed)

            val consumptionToSave = ConsumptionMapper.mapToSaveConsumption(traceabilityId, userId, consumption).apply {
                this.billId = billId
                residentialBasicCubicMeters =
                    currentBill.residentialBasicCubicMeters?.times(percentageConsumed!!)
                residentialBasicSuperiorCubicMeters =
                    currentBill.residentialBasicSuperiorCubicMeters?.times(percentageConsumed!!)
                notResidentialCubicMeters = currentBill.notResidentialCubicMeters?.times(percentageConsumed!!)
            }

            consumptionRepository.save(consumptionToSave)
        }
    }

    private fun getConsumptionByBillIdAndApartmentId(billId: Int, apartmentId: String): Consumption {
        val consumption = consumptionRepository.findByBillIdAndApartmentId(billId, apartmentId)
            ?: run {
                throw ErrorInfo.ERROR_CONSUMPTIONS_NOT_CREATED.buildIdlException()
            }

        return consumption
    }

    private fun calculateConsumptions(
        currentBill: Bill,
        currentConsumptions: List<ConsumptionInfo>
    ): Map<String, Double> {
        return try {
            val previousBill = billService.getPreviousBill(currentBill.startDate!!)

            val calculatedConsumptions = hashMapOf<String, Double>()

            currentConsumptions.forEach {
                val previousConsumption =
                    consumptionRepository.findByBillIdAndApartmentId(previousBill.billId!!, it.apartmentId!!)

                calculatedConsumptions[it.apartmentId!!] = it.value?.minus(previousConsumption?.value ?: 0.0)!!
            }

            calculatedConsumptions
        } catch (ex: IdlException) {
            currentConsumptions.associate { it.apartmentId!! to it.value!! }
        }
    }
}