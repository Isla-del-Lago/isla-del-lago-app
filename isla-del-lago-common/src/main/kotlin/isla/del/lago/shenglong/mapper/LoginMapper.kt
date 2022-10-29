package isla.del.lago.shenglong.mapper

import isla.del.lago.shenglong.request.security.LoginResponse

object LoginMapper {

    fun mapToLoginResponse(authToken: String) = LoginResponse().apply {
        this.authToken = authToken
    }
}