package isla.del.lago.shenglong.service.impl

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.environment.ShenglongEnvironment
import isla.del.lago.shenglong.exception.ErrorInfo
import isla.del.lago.shenglong.extensions.objectToJson
import isla.del.lago.shenglong.mapper.LoginMapper
import isla.del.lago.shenglong.model.User
import isla.del.lago.shenglong.repository.UserRepository
import isla.del.lago.shenglong.request.security.LoginRequest
import isla.del.lago.shenglong.request.security.LoginResponse
import isla.del.lago.shenglong.service.SecurityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.validation.constraints.NotBlank

@Service
class SecurityServiceImpl : SecurityService {

    companion object {
        private val logger = LoggerFactory.getLogger(SecurityServiceImpl::class.java)
    }

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun login(loginRequest: LoginRequest): LoginResponse {
        logger.info("--SecurityService:Login --LoginInfo:[{}]", loginRequest.objectToJson())

        return userRepository.findByEmail(loginRequest.email!!)?.let { user ->
            if (loginRequest.password != user.password) {
                throw ErrorInfo.ERROR_INVALID_LOGIN_CREDENTIALS.buildIdlException()
            }

            LoginMapper.mapToLoginResponse(buildJwtToken(user))
        } ?: throw ErrorInfo.ERROR_INVALID_LOGIN_CREDENTIALS.buildIdlException()
    }

    override fun validateToken(@NotBlank token: String) {
        logger.info("--SercurityService:ValidateToken --Token:[{}]", token)
    }

    private fun buildJwtToken(user: User): String = Jwts.builder()
        .setSubject(user.email)
        .claim(Constant.Jwt.Claims.USER_ID_CLAIM, user.userId)
        .claim(Constant.Jwt.Claims.EMAIL_CLAIM, user.email)
        .setIssuer(Constant.Jwt.ISSUER)
        .setIssuedAt(Date())
        .setExpiration(Date(System.currentTimeMillis() + Constant.Jwt.EXPIRATION_TIME))
        .signWith(Keys.hmacShaKeyFor(ShenglongEnvironment.jwtSignatureSecret?.byteInputStream(StandardCharsets.UTF_8)?.readAllBytes()))
        .compact()
}