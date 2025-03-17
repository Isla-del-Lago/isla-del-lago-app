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

@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var billId: Int? = null
    var traceabilityId: String? = null
    var startDate: String? = null
    var endDate: String? = null
    var residentialBasicCubicMeters: Float? = null
    var residentialBasicSuperiorCubicMeters: Float? = null
    var notResidentialCubicMeters: Float? = null
    var additionalDiscounts: Float? = null
    var discounts: Float? = null
    var residentialFixedAqueduct: Float? = null
    var residentialBasicAqueduct: Float? = null
    var residentialBasicSuperiorAqueduct: Float? = null
    var notResidentialFixedAqueduct: Float? = null
    var notResidentialAqueduct: Float? = null
    var residentialFixedSewerage: Float? = null
    var residentialBasicSewerage: Float? = null
    var residentialBasicSuperiorSewerage: Float? = null
    var notResidentialFixedSewerage: Float? = null
    var notResidentialSewerage: Float? = null
    var cleaning: Int? = null
    var createdBy: String? = null
    var updatedBy: String? = null
    var creationDate: Timestamp = Timestamp.from(ZonedDateTime.now(ZoneId.of("America/Bogota")).toInstant())
    var updateDate: Timestamp = Timestamp.from(ZonedDateTime.now(ZoneId.of("America/Bogota")).toInstant())
}