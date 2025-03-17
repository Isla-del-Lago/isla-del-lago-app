package isla.del.lago.shenglong.response.consumption

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ConsumptionDetailResponse {

    var residentialBasicCubicMeters: Double? = 0.0
    var residentialBasicSuperiorCubicMeters: Double? = 0.0
    var notResidentialCubicMeters: Double? = 0.0
    var additionalDiscounts: Double? = 0.0
    var discounts: Double? = 0.0
    var residentialFixedAqueduct: Double? = 0.0
    var residentialBasicAqueduct: Double? = 0.0
    var residentialBasicSuperiorAqueduct: Double? = 0.0
    var notResidentialFixedAqueduct: Double? = 0.0
    var notResidentialAqueduct: Double? = 0.0
    var residentialFixedSewerage: Double? = 0.0
    var residentialBasicSewerage: Double? = 0.0
    var residentialBasicSuperiorSewerage: Double? = 0.0
    var notResidentialFixedSewerage: Double? = 0.0
    var notResidentialSewerage: Double? = 0.0
    var cleaning: Double? = 0.0
    var total: Double? = 0.0

    fun calculateTotal(isToCalculateTotalBill: Boolean): Double {
        val totalCalculated =
            residentialFixedAqueduct!! + residentialBasicAqueduct!! + residentialBasicSuperiorAqueduct!! +
                    notResidentialFixedAqueduct!! + notResidentialAqueduct!! + residentialFixedSewerage!! +
                    notResidentialFixedSewerage!! + notResidentialSewerage!! + residentialBasicSewerage!! +
                    residentialBasicSuperiorSewerage!! + cleaning!! - discounts!! - additionalDiscounts!!

        if (isToCalculateTotalBill) return totalCalculated

        if (totalCalculated < 0) return 0.0

        return totalCalculated
    }

    fun updateFromPreviousResponse(previous: ConsumptionDetailResponse) {
        residentialBasicCubicMeters = residentialBasicCubicMeters?.plus(previous.residentialBasicCubicMeters!!)
        residentialBasicSuperiorCubicMeters = residentialBasicSuperiorCubicMeters?.plus(previous.residentialBasicSuperiorCubicMeters!!)
        notResidentialCubicMeters = notResidentialCubicMeters?.plus(previous.notResidentialCubicMeters!!)
        additionalDiscounts = additionalDiscounts?.plus(previous.additionalDiscounts!!)
        discounts = discounts?.plus(previous.discounts!!)
        residentialFixedAqueduct = residentialFixedAqueduct?.plus(previous.residentialFixedAqueduct!!)
        residentialBasicAqueduct = residentialBasicAqueduct?.plus(previous.residentialBasicAqueduct!!)
        residentialBasicSuperiorAqueduct = residentialBasicSuperiorAqueduct?.plus(previous.residentialBasicSuperiorAqueduct!!)
        notResidentialFixedAqueduct = notResidentialFixedAqueduct?.plus(previous.notResidentialFixedAqueduct!!)
        notResidentialAqueduct = notResidentialAqueduct?.plus(previous.notResidentialAqueduct!!)
        residentialFixedSewerage = residentialFixedSewerage?.plus(previous.residentialFixedSewerage!!)
        residentialBasicSewerage = residentialBasicSewerage?.plus(previous.residentialBasicSewerage!!)
        residentialBasicSuperiorSewerage = residentialBasicSuperiorSewerage?.plus(previous.residentialBasicSuperiorSewerage!!)
        notResidentialFixedSewerage = notResidentialFixedSewerage?.plus(previous.notResidentialFixedSewerage!!)
        notResidentialSewerage = notResidentialSewerage?.plus(previous.notResidentialSewerage!!)
        cleaning = cleaning?.plus(previous.cleaning!!)
        total = total?.plus(previous.total!!)
    }
}