package isla.del.lago.shenglong.response.consumption

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ConsumptionDetailByApartmentResponse {
    var billId: Int? = null
    var startDate: String? = null
    var endDate: String? = null
    var consumptionDetail: ConsumptionDetailResponse? = null
}