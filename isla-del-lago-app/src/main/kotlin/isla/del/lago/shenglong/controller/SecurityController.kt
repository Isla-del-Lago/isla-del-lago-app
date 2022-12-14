package isla.del.lago.shenglong.controller

import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.request.security.LoginRequest
import isla.del.lago.shenglong.route.Route
import isla.del.lago.shenglong.service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping(value = [Route.BASE_PATH + Route.Security.BASE_PATH])
@CrossOrigin("*")
class SecurityController {

    @Autowired
    private lateinit var securityService: SecurityService

    @PostMapping(Route.Security.LOGIN)
    fun login(@Validated @RequestBody body: LoginRequest) =
        ResponseEntity.status(HttpStatus.OK).body(securityService.login(body))

    @PostMapping(Route.Security.Token.REFRESH)
    fun refreshToken(@NotBlank @RequestHeader(Constant.Header.USER_ID) userId: String) =
        ResponseEntity.status(HttpStatus.OK).body(securityService.refreshToken(userId))
}