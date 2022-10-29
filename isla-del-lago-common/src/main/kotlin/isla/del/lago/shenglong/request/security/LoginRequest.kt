package isla.del.lago.shenglong.request.security

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class LoginRequest {

    @NotBlank
    @Email
    var email: String? = null

    @NotBlank
    var password: String? = null
}