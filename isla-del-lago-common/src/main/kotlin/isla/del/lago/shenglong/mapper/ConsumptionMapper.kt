package isla.del.lago.shenglong.mapper

import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.model.Bill
import isla.del.lago.shenglong.model.Consumption
import isla.del.lago.shenglong.request.consumption.ConsumptionInfo
import isla.del.lago.shenglong.response.consumption.ConsumptionDetailResponse

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
        notResidentialCubicMeters = consumption.notResidentialCubicMeters
        discounts = (bill.discounts?.div(Constant.Consumption.MAX_CONSUMPTION_SIZE))?.toDouble()
        residentialFixedAqueduct = (bill.residentialFixedAqueduct?.div(10))?.toDouble()
        residentialBasicAqueduct =
            bill.residentialBasicAqueduct?.times(consumption.residentialBasicCubicMeters!!)
        residentialBasicSuperiorAqueduct =
            bill.residentialBasicSuperiorAqueduct?.times(consumption.residentialBasicSuperiorCubicMeters!!)
        notResidentialFixedAqueduct =
            (bill.notResidentialFixedAqueduct?.div(Constant.Consumption.MAX_CONSUMPTION_SIZE))?.toDouble()
        notResidentialAqueduct = bill.notResidentialAqueduct?.times(consumption.notResidentialCubicMeters!!)
        residentialFixedSewerage = bill.residentialFixedSewerage?.div(Constant.Consumption.MAX_CONSUMPTION_SIZE)?.toDouble()
        residentialBasicSewerage =
            bill.residentialBasicSewerage?.times(consumption.residentialBasicCubicMeters!!)
        residentialBasicSuperiorSewerage =
            bill.residentialBasicSuperiorSewerage?.times(consumption.residentialBasicSuperiorCubicMeters!!)
        notResidentialFixedSewerage =
            (bill.notResidentialFixedSewerage?.div(Constant.Consumption.MAX_CONSUMPTION_SIZE))?.toDouble()
        notResidentialSewerage = bill.notResidentialSewerage?.times(consumption.notResidentialCubicMeters!!)
        cleaning = bill.cleaning?.div(Constant.Consumption.MAX_CONSUMPTION_SIZE)?.toDouble()
        total = this.calculateTotal()
    }
}