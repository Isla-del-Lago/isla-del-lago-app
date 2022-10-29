package isla.del.lago.shenglong.request.bill

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class CreateBillRequest {

    @field:NotNull
    var startDate: LocalDate? = null

    @field:NotNull
    var endDate: LocalDate? = null

    @field:PositiveOrZero
    @field:NotNull
    var residentialBasicCubicMeters: Int? = null

    @field:PositiveOrZero
    @field:NotNull
    var residentialBasicSuperiorCubicMeters: Int? = null

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
    var residentialFixedSewerage: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var residentialBasicSewerage: Float? = null

    @field:DecimalMin(value = "0.0")
    @field:NotNull
    var residentialBasicSuperiorSewerage: Float? = null

    @field:NotNull
    @field:PositiveOrZero
    var cleaning: Int? = null
}