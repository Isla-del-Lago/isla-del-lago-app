package isla.del.lago.shenglong.request.bill

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.PositiveOrZero

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class CreateBillRequest {

    @field:NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}\$")
    var startDate: String? = null

    @field:NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}\$")
    var endDate: String? = null

    @field:PositiveOrZero
    @field:NotNull
    var residentialBasicCubicMeters: Float? = null

    @field:PositiveOrZero
    @field:NotNull
    var residentialBasicSuperiorCubicMeters: Float? = null

    @field:PositiveOrZero
    @field:NotNull
    var notResidentialCubicMeters: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var discounts: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var residentialFixedAqueduct: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var residentialBasicAqueduct: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var residentialBasicSuperiorAqueduct: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var notResidentialFixedAqueduct: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var notResidentialAqueduct: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var residentialFixedSewerage: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var residentialBasicSewerage: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var residentialBasicSuperiorSewerage: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var notResidentialFixedSewerage: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var notResidentialSewerage: Float? = null

    @field:NotNull
    @field:PositiveOrZero
    var cleaning: Int? = null
}