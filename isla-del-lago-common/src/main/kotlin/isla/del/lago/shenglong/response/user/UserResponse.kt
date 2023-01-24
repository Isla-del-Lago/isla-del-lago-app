package isla.del.lago.shenglong.response.user

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class UserResponse {
    var userId: String? = null
    var email: String? = null
    var name: String? = null
    var role: String? = null
}