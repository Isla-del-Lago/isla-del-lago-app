package isla.del.lago.shenglong.request.user

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class CreateUserRequest {

    @NotBlank
    @Email
    var email: String? = null

    @NotBlank
    @Length(min = 10, max = 30)
    var password: String? = null
}