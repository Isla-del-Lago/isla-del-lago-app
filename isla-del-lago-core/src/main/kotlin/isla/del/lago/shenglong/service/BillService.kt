package isla.del.lago.shenglong.service

import isla.del.lago.shenglong.model.Bill
import isla.del.lago.shenglong.request.bill.CreateBillRequest
import isla.del.lago.shenglong.response.bill.BillResponse
import isla.del.lago.shenglong.response.bill.DeleteBillResponse

interface BillService {

    fun createBill(userId: String, createBillRequest: CreateBillRequest): BillResponse

    fun getAllBills(): List<BillResponse>

    fun deleteBillById(billId: Int): DeleteBillResponse

    fun getBillById(id: Int): Bill

    fun getPreviousBill(billStartDate: String): BillResponse
}