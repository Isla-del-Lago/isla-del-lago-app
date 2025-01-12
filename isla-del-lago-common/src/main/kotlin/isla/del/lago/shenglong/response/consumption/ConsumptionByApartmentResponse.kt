package isla.del.lago.shenglong.response.consumption

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ConsumptionByApartmentResponse {

    var apartmentId: String? = null
    var consumption: ConsumptionResponse? = null
}