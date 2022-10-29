package isla.del.lago.shenglong.interceptor

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
        logger.info("--TokenInterceptor:PreHandle --ValidateToken --HttpMethod:[{}] --Url:[{}]",
            request.method, request.requestURL)

        val authToken: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        authToken?.let {
            securityService.validateToken(it)

            return true
        }.run {
            throw ErrorInfo.ERROR_INVALID_TOKEN.buildIdlException()
        }
    }
}