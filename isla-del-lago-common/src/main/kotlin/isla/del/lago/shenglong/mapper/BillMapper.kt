package isla.del.lago.shenglong.mapper

import isla.del.lago.shenglong.model.Bill
import isla.del.lago.shenglong.request.bill.CreateBillRequest
import isla.del.lago.shenglong.response.bill.BillResponse

object BillMapper {

    fun mapToSaveBill(traceabilityId: String, userId: String, createBillRequest: CreateBillRequest) = Bill().apply {
        this.traceabilityId = traceabilityId
        startDate = createBillRequest.startDate
        endDate = createBillRequest.endDate
        residentialBasicCubicMeters = createBillRequest.residentialBasicCubicMeters
        residentialBasicSuperiorCubicMeters = createBillRequest.residentialBasicSuperiorCubicMeters
        notResidentialCubicMeters = createBillRequest.notResidentialCubicMeters
        additionalDiscounts = createBillRequest.additionalDiscounts
        discounts = createBillRequest.discounts
        residentialFixedAqueduct = createBillRequest.residentialFixedAqueduct
        residentialBasicAqueduct = createBillRequest.residentialBasicAqueduct
        residentialBasicSuperiorAqueduct = createBillRequest.residentialBasicSuperiorAqueduct
        notResidentialFixedAqueduct = createBillRequest.notResidentialFixedAqueduct
        notResidentialAqueduct = createBillRequest.notResidentialAqueduct
        residentialFixedSewerage = createBillRequest.residentialFixedSewerage
        residentialBasicSewerage = createBillRequest.residentialBasicSewerage
        residentialBasicSuperiorSewerage = createBillRequest.residentialBasicSuperiorSewerage
        notResidentialFixedSewerage = createBillRequest.notResidentialFixedSewerage
        notResidentialSewerage = createBillRequest.notResidentialSewerage
        cleaning = createBillRequest.cleaning
        createdBy = userId
        updatedBy = userId
    }

    fun mapToBillResponse(bill: Bill) = BillResponse().apply {
        billId = bill.billId
        startDate = bill.startDate
        endDate = bill.endDate
    }
}