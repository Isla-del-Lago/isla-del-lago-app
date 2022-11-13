package isla.del.lago.shenglong.mapper

import isla.del.lago.shenglong.request.security.LoginResponse

object LoginMapper {

    fun mapToLoginResponse(userId: String, authToken: String) = LoginResponse().apply {
        this.userId = userId
        this.authToken = authToken
    }
}