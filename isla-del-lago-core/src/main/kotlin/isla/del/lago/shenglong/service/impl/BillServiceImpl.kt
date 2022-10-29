package isla.del.lago.shenglong.service.impl

import isla.del.lago.shenglong.extensions.objectToJson
import isla.del.lago.shenglong.mapper.BillMapper
import isla.del.lago.shenglong.repository.BillRepository
import isla.del.lago.shenglong.request.bill.CreateBillRequest
import isla.del.lago.shenglong.response.bill.BillResponse
import isla.del.lago.shenglong.response.bill.DeleteBillResponse
import isla.del.lago.shenglong.service.BillService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BillServiceImpl : BillService {

    companion object {
        private val logger = LoggerFactory.getLogger(BillServiceImpl::class.java)
    }

    @Autowired
    private lateinit var billRepository: BillRepository

    override fun createBill(createBillRequest: CreateBillRequest): BillResponse {
        logger.info("--BillService:CreateBill --BillInfo:[{}]", createBillRequest.objectToJson())

        return billRepository.save(BillMapper.mapToSaveBill(createBillRequest)).let {
            BillMapper.mapToBillResponse(it)
        }
    }

    override fun getAllBills(): List<BillResponse> {
        logger.info("--BillService:GetAllBills")

        return billRepository.findAll().map { bill -> BillMapper.mapToBillResponse(bill) }
    }

    override fun deleteBillById(billId: Int): DeleteBillResponse {
        logger.info("--BillService:DeleteBillById --BillId:[{}]", billId)

        billRepository.deleteById(billId)

        return DeleteBillResponse().apply { this.billId = billId }
    }
}