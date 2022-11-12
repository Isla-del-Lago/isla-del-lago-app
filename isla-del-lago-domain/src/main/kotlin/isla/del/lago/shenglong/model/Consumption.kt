package isla.del.lago.shenglong.model

import java.sql.Timestamp
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var traceabilityId: String? = null
    var apartmentId: String? = null
    var billId: Int? = null
    var value: Double? = null
    var residentialBasicCubicMeters: Double? = null
    var residentialBasicSuperiorCubicMeters: Double? = null
    var createdAt: Timestamp = Timestamp.from(ZonedDateTime.now(ZoneId.of("America/Bogota")).toInstant())
    var updatedAt: Timestamp = Timestamp.from(ZonedDateTime.now(ZoneId.of("America/Bogota")).toInstant())
    var createdBy: String? = null
    var updatedBy: String? = null
}