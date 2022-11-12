package isla.del.lago.shenglong.mapper

import isla.del.lago.shenglong.model.Consumption
import isla.del.lago.shenglong.request.consumption.ConsumptionInfo

object ConsumptionMapper {

    fun mapToSaveConsumption(traceabilityId: String, userId: String, consumptionInfo: ConsumptionInfo) = Consumption().apply {
        this.traceabilityId = traceabilityId
        apartmentId = consumptionInfo.apartmentId
        createdBy = userId
        updatedBy = userId
        value = consumptionInfo.value
    }
}