package isla.del.lago.shenglong.mapper

import isla.del.lago.shenglong.model.User
import isla.del.lago.shenglong.request.user.CreateUserRequest
import isla.del.lago.shenglong.response.user.UserResponse
import java.util.*

object UserMapper {

    fun mapToSaveUser(createUserRequest: CreateUserRequest) = User().apply {
        userId = UUID.randomUUID().toString()
        email = createUserRequest.email
        password = createUserRequest.password
        name = createUserRequest.name
        role = createUserRequest.role?.name
    }

    fun mapToUserResponse(user: User) = UserResponse().apply {
        userId = user.userId
        email = user.email
        name = user.name
        role = user.role
    }
}