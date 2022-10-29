package isla.del.lago.shenglong.configuration

import isla.del.lago.shenglong.interceptor.TokenInterceptor
import isla.del.lago.shenglong.route.Route
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AuthenticationInterceptorConfiguration : WebMvcConfigurer {

    @Autowired
    private lateinit var tokenInterceptor: TokenInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(tokenInterceptor)
            .addPathPatterns(Route.BASE_PATH + "/**")
            .excludePathPatterns(
                Route.BASE_PATH + Route.Health.BASE_PATH,
                Route.BASE_PATH + Route.Security.BASE_PATH + Route.Security.LOGIN,
            )
    }
}