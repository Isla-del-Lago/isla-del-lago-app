package isla.del.lago.shenglong.service

import isla.del.lago.shenglong.request.security.LoginRequest
import isla.del.lago.shenglong.request.security.LoginResponse

interface SecurityService {

    fun login(loginRequest: LoginRequest): LoginResponse

    fun validateToken(token: String): Boolean
}