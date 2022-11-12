package isla.del.lago.shenglong.mapper

import isla.del.lago.shenglong.model.Bill
import isla.del.lago.shenglong.model.Consumption
import isla.del.lago.shenglong.request.consumption.ConsumptionInfo
import isla.del.lago.shenglong.response.ConsumptionDetailResponse

object ConsumptionMapper {

    fun mapToSaveConsumption(traceabilityId: String, userId: String, consumptionInfo: ConsumptionInfo) =
        Consumption().apply {
            this.traceabilityId = traceabilityId
            apartmentId = consumptionInfo.apartmentId
            createdBy = userId
            updatedBy = userId
            value = consumptionInfo.value
        }

    fun mapToConsumptionDetailResponse(bill: Bill, consumption: Consumption) = ConsumptionDetailResponse().apply {
        residentialBasicCubicMeters = consumption.residentialBasicCubicMeters
        residentialBasicSuperiorCubicMeters = consumption.residentialBasicSuperiorCubicMeters
        discounts = (bill.discounts?.div(10))?.toDouble()
        residentialFixedAqueduct = (bill.residentialFixedAqueduct?.div(10))?.toDouble()
        residentialBasicAqueduct =
            bill.residentialBasicAqueduct?.times(consumption.residentialBasicCubicMeters!!)
        residentialBasicSuperiorAqueduct =
            bill.residentialBasicSuperiorAqueduct?.times(consumption.residentialBasicSuperiorCubicMeters!!)
        residentialFixedSewerage = bill.residentialFixedSewerage?.div(10)?.toDouble()
        residentialBasicSewerage =
            bill.residentialBasicSewerage?.times(consumption.residentialBasicCubicMeters!!)
        residentialBasicSuperiorSewerage =
            bill.residentialBasicSuperiorSewerage?.times(consumption.residentialBasicSuperiorCubicMeters!!)
        cleaning = bill.cleaning?.div(10)?.toDouble()
        total = this.calculateTotal()
    }
}