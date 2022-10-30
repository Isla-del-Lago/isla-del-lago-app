package isla.del.lago.shenglong.service.impl

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
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
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.validation.constraints.NotBlank

@Service
@DependsOn("ShenglongEnvironment")
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
                logger.error("--SecurityService:Login --Invalid Login Credentials --LoginInfo:[{}]",
                    loginRequest.objectToJson())

                throw ErrorInfo.ERROR_INVALID_LOGIN_CREDENTIALS.buildIdlException()
            }

            LoginMapper.mapToLoginResponse(buildJwtToken(user))
        } ?: run {
            logger.error("--SecurityService:Login --Invalid Login Credentials --LoginInfo:[{}]",
                loginRequest.objectToJson())

            throw ErrorInfo.ERROR_INVALID_LOGIN_CREDENTIALS.buildIdlException()
        }
    }

    override fun validateToken(@NotBlank token: String): Boolean {
        logger.info("--SecurityService:ValidateToken")

        try {
            val key = Keys.hmacShaKeyFor(
                ShenglongEnvironment.jwtSignatureSecret?.byteInputStream(StandardCharsets.UTF_8)?.readAllBytes()
            )

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)

            return true
        } catch (ex: SignatureException) {
            logger.error("--SecurityService:ValidateToken --Invalid JWT Token --Error:[{}]", ex.message)
        } catch (ex: MalformedJwtException) {
            logger.error("--SecurityService:ValidateToken --Invalid JWT Token --Error:[{}]", ex.message)
        } catch (ex: ExpiredJwtException) {
            logger.error("--SecurityService:ValidateToken --Expired JWT Token --Error:[{}]", ex.message)
        } catch (ex: UnsupportedJwtException) {
            logger.error("--SecurityService:ValidateToken --Unsupported JWT Token --Error:[{}]", ex.message)
        } catch (ex: IllegalArgumentException) {
            logger.error("--SecurityService:ValidateToken --JWT Claims String Is Empty --Error:[{}]", ex.message)
        }

        logger.warn("--SecurityService:ValidateToken --Invalid Token")

        throw ErrorInfo.ERROR_INVALID_TOKEN.buildIdlException()
    }

    private fun buildJwtToken(user: User): String = Jwts.builder()
        .setSubject(user.email)
        .claim(Constant.Jwt.Claims.USER_ID_CLAIM, user.userId)
        .claim(Constant.Jwt.Claims.EMAIL_CLAIM, user.email)
        .setIssuer(Constant.Jwt.ISSUER)
        .setIssuedAt(Date())
        .setExpiration(Date(System.currentTimeMillis() + Constant.Jwt.EXPIRATION_TIME))
        .signWith(
            Keys.hmacShaKeyFor(
                ShenglongEnvironment.jwtSignatureSecret?.byteInputStream(StandardCharsets.UTF_8)?.readAllBytes()
            )
        )
        .compact()
}