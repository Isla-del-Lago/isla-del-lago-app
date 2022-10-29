package isla.del.lago.shenglong.request.security

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import isla.del.lago.shenglong.Constant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.validation.constraints.NotBlank

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class LoginResponse {

    @NotBlank
    var authToken: String? = null
    var expirationDate: LocalDateTime = Date(System.currentTimeMillis() + Constant.Jwt.EXPIRATION_TIME)
        .toInstant()
        .atZone(ZoneId.of("America/Bogota"))
        .toLocalDateTime()
}