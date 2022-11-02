package isla.del.lago.shenglong.interceptor

import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.exception.ErrorInfo
import isla.del.lago.shenglong.service.SecurityService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class TokenInterceptor : HandlerInterceptor {

    companion object {
        private val logger = LoggerFactory.getLogger(TokenInterceptor::class.java)
    }

    @Autowired
    private lateinit var securityService: SecurityService

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val userId = request.getHeader(Constant.Header.USER_ID)
            ?: run {
                logger.error("--TokenInterceptor:PreHandle --UserId Is Not Present")
                throw ErrorInfo.ERROR_INVALID_REQUEST.buildIdlException()
            }

        val authToken: String = request.getHeader(HttpHeaders.AUTHORIZATION)?.let {
            it.split(Constant.SPACE_SEPARATOR)[1].takeIf { token -> token.isNotBlank() }
        } ?: run {
            logger.error(
                "--TokenInterceptor:PreHandle --Token Value Is Not Present --UserId:[{}]",
                userId
            )

            throw ErrorInfo.ERROR_INVALID_TOKEN.buildIdlException()
        }

        return authToken.let { securityService.validateToken(userId, it) }
    }
}