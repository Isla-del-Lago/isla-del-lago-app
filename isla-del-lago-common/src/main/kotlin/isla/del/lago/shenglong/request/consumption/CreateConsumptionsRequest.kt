package isla.del.lago.shenglong.request.consumption

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class CreateConsumptionsRequest {

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 10)
    var consumptions: List<@Valid ConsumptionInfo>? = null
}