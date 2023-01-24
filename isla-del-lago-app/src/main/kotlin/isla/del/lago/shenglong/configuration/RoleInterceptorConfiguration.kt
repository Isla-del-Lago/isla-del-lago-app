package isla.del.lago.shenglong.configuration

import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.exception.ErrorInfo
import isla.del.lago.shenglong.route.Route
import isla.del.lago.shenglong.service.SecurityService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.HandlerMapping
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class RoleInterceptorConfiguration(private val roleInterceptor: RoleInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(roleInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns(
                Route.BASE_PATH + Route.Health.BASE_PATH,
                Route.BASE_PATH + Route.Security.BASE_PATH + Route.Security.LOGIN,
            )
    }
}

@Service
class RoleInterceptor(private val securityService: SecurityService) : HandlerInterceptor {

    companion object {
        private val logger = LoggerFactory.getLogger(RoleInterceptor::class.java)
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val userId = request.getHeader(Constant.Header.USER_ID)
            ?: run {
                logger.error("--TokenInterceptor:PreHandle --UserId Is Not Present")
                throw ErrorInfo.ERROR_INVALID_REQUEST.buildIdlException()
            }

        val path = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE) as String

        return securityService.validateUserRole(userId, path, request.method)
    }
}