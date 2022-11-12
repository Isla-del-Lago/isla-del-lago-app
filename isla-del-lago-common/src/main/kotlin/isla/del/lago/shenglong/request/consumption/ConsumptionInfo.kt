package isla.del.lago.shenglong.request.consumption

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import isla.del.lago.shenglong.Constant
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ConsumptionInfo {

    @Positive
    @NotNull
    var value: Double? = null

    @NotBlank
    @Pattern(regexp = Constant.Regex.APARTMENT_REGEX)
    var apartmentId: String? = null
}