package isla.del.lago.shenglong.mapper

import isla.del.lago.shenglong.request.security.LoginResponse
import java.util.*

object LoginMapper {

    fun mapToLoginResponse(userId: String, authToken: String) = LoginResponse().apply {
        this.userId = userId
        this.authToken = authToken
    }
}