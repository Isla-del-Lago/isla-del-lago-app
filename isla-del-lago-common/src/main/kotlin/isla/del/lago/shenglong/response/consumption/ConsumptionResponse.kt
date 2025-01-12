package isla.del.lago.shenglong.response.consumption

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ConsumptionResponse {

    var value: Double? = 0.0
}