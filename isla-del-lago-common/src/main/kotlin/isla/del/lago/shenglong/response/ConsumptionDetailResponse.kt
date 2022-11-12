package isla.del.lago.shenglong.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ConsumptionDetailResponse {

    var residentialBasicCubicMeters: Double? = null
    var residentialBasicSuperiorCubicMeters: Double? = null
    var discounts: Double? = null
    var residentialFixedAqueduct: Double? = null
    var residentialBasicAqueduct: Double? = null
    var residentialBasicSuperiorAqueduct: Double? = null
    var residentialFixedSewerage: Double? = null
    var residentialBasicSewerage: Double? = null
    var residentialBasicSuperiorSewerage: Double? = null
    var cleaning: Double? = null
    var total: Double? = null

    fun calculateTotal(): Double {
        val totalCalculated =
            residentialFixedAqueduct!! + residentialBasicAqueduct!! + residentialBasicSuperiorAqueduct!! +
                    residentialFixedSewerage!! + residentialBasicSewerage!! + residentialBasicSuperiorSewerage!! +
                    cleaning!! - discounts!!
        if (totalCalculated < 0) return 0.0

        return totalCalculated
    }
}