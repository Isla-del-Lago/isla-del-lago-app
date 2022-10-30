package isla.del.lago.shenglong.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.sql.Timestamp
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class User {

    @Id
    @NotNull
    var userId: String? = null

    @NotBlank
    @Email
    var email: String? = null

    @NotBlank
    var password: String? = null

    @NotBlank
    var creationDate: Timestamp = Timestamp.from(ZonedDateTime.now(ZoneId.of("America/Bogota")).toInstant())

    @NotBlank
    var updateDate: Timestamp = Timestamp.from(ZonedDateTime.now(ZoneId.of("America/Bogota")).toInstant())
}