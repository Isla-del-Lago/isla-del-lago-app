package isla.del.lago.shenglong.controller

import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.request.user.CreateUserRequest
import isla.del.lago.shenglong.route.Route
import isla.del.lago.shenglong.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
@RequestMapping(value = [Route.BASE_PATH + Route.User.BASE_PATH])
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping
    fun createUser(@RequestBody body: CreateUserRequest) =
        ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(body))

    @GetMapping
    fun getAllUsers() = ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers())

    @DeleteMapping(Route.User.BY_USER_ID)
    fun deleteUserById(@PathParam(Constant.PathParam.USER_ID) userId: Int) =
        ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserById(userId))
}