package isla.del.lago.shenglong.response.apartment

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class ApartmentInfoResponse {
    var apartmentId: String? = null
    var apartmentName: String? = null
}