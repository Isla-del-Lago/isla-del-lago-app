package isla.del.lago.shenglong.controller

import isla.del.lago.shenglong.route.Route
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [Route.BASE_PATH + Route.Health.BASE_PATH])
@CrossOrigin("*")
class HealthController {

    @GetMapping
    fun health(): ResponseEntity<String> = ResponseEntity.status(HttpStatus.OK).body("Is healthy")
}