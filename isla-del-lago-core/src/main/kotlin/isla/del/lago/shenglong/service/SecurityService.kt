package isla.del.lago.shenglong.service

import isla.del.lago.shenglong.request.security.LoginRequest
import isla.del.lago.shenglong.request.security.LoginResponse

interface SecurityService {

    fun login(loginRequest: LoginRequest): LoginResponse

    fun validateToken(userId: String, token: String): Boolean

    fun refreshToken(userId: String): LoginResponse

    fun validateUserRole(userId: String, path: String, httpMethod: String): Boolean
}