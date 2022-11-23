package isla.del.lago.shenglong.service.impl

import isla.del.lago.shenglong.exception.ErrorInfo
import isla.del.lago.shenglong.extensions.objectToJson
import isla.del.lago.shenglong.mapper.BillMapper
import isla.del.lago.shenglong.model.Bill
import isla.del.lago.shenglong.repository.BillRepository
import isla.del.lago.shenglong.request.bill.CreateBillRequest
import isla.del.lago.shenglong.response.bill.BillResponse
import isla.del.lago.shenglong.response.bill.DeleteBillResponse
import isla.del.lago.shenglong.service.BillService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class BillServiceImpl : BillService {

    companion object {
        private val logger = LoggerFactory.getLogger(BillServiceImpl::class.java)
    }

    @Autowired
    private lateinit var billRepository: BillRepository

    override fun createBill(
        userId: String,
        createBillRequest: CreateBillRequest
    ): BillResponse {
        val traceabilityId = UUID.randomUUID().toString()

        logger.info(
            "--BillService:CreateBill --TraceabilityId:[{}] --UserId:[{}] --BillInfo:[{}]",
            traceabilityId, userId, createBillRequest.objectToJson()
        )

        if (billRepository.existsByStartDateAndEndDate(createBillRequest.startDate!!, createBillRequest.endDate!!)) {
            logger.warn(
                "--BillService:CreateBill --TraceabilityId:[{}] --UserId:[{}] --Bill Already Exists",
                traceabilityId, userId
            )

            throw ErrorInfo.ERROR_BILL_ALREADY_EXISTS.buildIdlException()
        }

        return billRepository.save(BillMapper.mapToSaveBill(traceabilityId, userId, createBillRequest)).let {
            BillMapper.mapToBillResponse(it)
        }
    }

    override fun getAllBills(userId: String): List<BillResponse> {
        logger.info(
            "--BillService:GetAllBills --UserId:[{}]",
            userId
        )

        return billRepository.findAll().map { bill -> BillMapper.mapToBillResponse(bill) }
    }

    override fun deleteBillById(userId: String, billId: Int): DeleteBillResponse {
        logger.info("--BillService:DeleteBillById --UserId:[{}] --BillId:[{}]", userId, billId)

        billRepository.deleteById(billId)

        return DeleteBillResponse().apply { this.billId = billId }
    }

    override fun getBillById(id: Int): Bill {
        logger.info("--BillService:GetBillById --BillId:[{}]", id)

        return billRepository.findById(id).orElseThrow { ErrorInfo.ERROR_BILL_DOES_NOT_EXISTS.buildIdlException() }
    }

    override fun getPreviousBill(billStartDate: String): BillResponse {
        logger.info("--BillService:GetPreviousBill --CurrentBillStartDate:[{}]", billStartDate)

        val previousBill = billRepository.findAll()
            .filter { LocalDate.parse(it.startDate).isBefore(LocalDate.parse(billStartDate)) }
            .sortedBy { LocalDate.parse(it.startDate) }
            .lastOrNull()
            ?: run {
                throw ErrorInfo.ERROR_INVALID_REQUEST.buildIdlException()
            }

        return BillMapper.mapToBillResponse(previousBill)
    }
}