package isla.del.lago.shenglong.service

import isla.del.lago.shenglong.request.bill.CreateBillRequest
import isla.del.lago.shenglong.response.bill.BillResponse
import isla.del.lago.shenglong.response.bill.DeleteBillResponse

interface BillService {

    fun createBill(createBillRequest: CreateBillRequest): BillResponse

    fun getAllBills(): List<BillResponse>

    fun deleteBillById(billId: Int): DeleteBillResponse
}