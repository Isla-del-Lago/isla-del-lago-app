package isla.del.lago.shenglong.service.impl

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.enums.RoleEnum
import isla.del.lago.shenglong.environment.ShenglongEnvironment
import isla.del.lago.shenglong.exception.ErrorInfo
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

@Service
@DependsOn("ShenglongEnvironment")
class SecurityServiceImpl : SecurityService {

    companion object {
        private val logger = LoggerFactory.getLogger(SecurityServiceImpl::class.java)
    }

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun login(loginRequest: LoginRequest): LoginResponse {
        logger.info("--SecurityService:Login --UserEmail:[{}]", loginRequest.email)

        return userRepository.findByEmail(loginRequest.email!!)?.let { user ->
            if (loginRequest.password != user.password) {
                logger.error(
                    "--SecurityService:Login --Invalid Login Credentials --UserEmail:[{}]",
                    loginRequest.email
                )

                throw ErrorInfo.ERROR_INVALID_LOGIN_CREDENTIALS.buildIdlException()
            }

            LoginMapper.mapToLoginResponse(user.userId!!, buildJwtToken(user))
        } ?: run {
            logger.error(
                "--SecurityService:Login --Invalid Login Credentials --UserEmail:[{}]",
                loginRequest.email
            )

            throw ErrorInfo.ERROR_INVALID_LOGIN_CREDENTIALS.buildIdlException()
        }
    }

    override fun validateToken(userId: String, token: String): Boolean {
        logger.info("--SecurityService:ValidateToken --UserId:[{}]", userId)

        try {
            val key = Keys.hmacShaKeyFor(
                ShenglongEnvironment.jwtSignatureSecret?.byteInputStream(StandardCharsets.UTF_8)?.readAllBytes()
            )

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)

            return true
        } catch (ex: SignatureException) {
            logger.error("--SecurityService:ValidateToken --Invalid JWT Token  --Error:[{}]", ex.message)
        } catch (ex: MalformedJwtException) {
            logger.error("--SecurityService:ValidateToken --Invalid JWT Token --Error:[{}]", ex.message)
        } catch (ex: ExpiredJwtException) {
            logger.error("--SecurityService:ValidateToken --Expired JWT Token --Error:[{}]", ex.message)
        } catch (ex: UnsupportedJwtException) {
            logger.error("--SecurityService:ValidateToken --Unsupported JWT Token --Error:[{}]", ex.message)
        } catch (ex: IllegalArgumentException) {
            logger.error("--SecurityService:ValidateToken --JWT Claims String Is Empty --Error:[{}]", ex.message)
        }

        logger.warn("--SecurityService:ValidateToken --Invalid Token --UserId:[{}]", userId)

        throw ErrorInfo.ERROR_INVALID_TOKEN.buildIdlException()
    }

    override fun refreshToken(userId: String): LoginResponse {
        return userRepository.findUserByUserId(userId)
            ?.let { LoginMapper.mapToLoginResponse(it.userId!!, buildJwtToken(it)) }
            ?: run {
                throw ErrorInfo.ERROR_INVALID_REQUEST.buildIdlException()
            }
    }

    override fun validateUserRole(userId: String, path: String, httpMethod: String): Boolean {
        return userRepository.findUserByUserId(userId)?.let { user ->
            val isPathAllowed = try {
                when (RoleEnum.valueOf(user.role!!)) {
                    RoleEnum.ROOT -> true
                    RoleEnum.ADMIN -> RoleEnum.validateAdminPaths(httpMethod, path)
                    RoleEnum.USER -> RoleEnum.validateUserPaths(httpMethod, path)
                    else -> false
                }
            } catch (ex: NullPointerException) {
                logger.error(
                    "--SecurityService:validateUserRole --Error Mapping Role --UserId:[{}] --Exception:[{}]",
                    userId, ex.message
                )

                throw ErrorInfo.ERROR_INVALID_REQUEST.buildIdlException()
            }

            if (isPathAllowed!!.not()) {
                logger.error(
                    "--SecurityService:validateUserRole --Forbidden Flow --UserId:[{}] --Path:[{}] --HttpMethod:[{}]",
                    userId, path, httpMethod
                )

                throw ErrorInfo.ERROR_UNAUTHORIZED_FLOW.buildIdlException()
            }

            return true
        } ?: run {
            logger.error("--SecurityService:validateUserRole --User Not Found --UserId:[{}}", userId)

            throw ErrorInfo.ERROR_USER_NOT_FOUND.buildIdlException()
        }
    }

    private fun buildJwtToken(user: User): String = Jwts.builder()
        .setSubject(user.email)
        .claim(Constant.Jwt.Claims.USER_ID_CLAIM, user.userId)
        .claim(Constant.Jwt.Claims.EMAIL_CLAIM, user.email)
        .claim(Constant.Jwt.Claims.USER_NAME_CLAIM, user.name)
        .claim(Constant.Jwt.Claims.USER_ROLE_CLAIM, user.role)
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