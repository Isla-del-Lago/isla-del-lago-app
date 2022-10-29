package isla.del.lago.shenglong.mapper

import isla.del.lago.shenglong.model.User
import isla.del.lago.shenglong.request.user.CreateUserRequest
import isla.del.lago.shenglong.response.user.UserResponse

object UserMapper {

    fun mapToSaveUser(createUserRequest: CreateUserRequest) = User().apply {
        email = createUserRequest.email
        password = createUserRequest.password
    }

    fun mapToUserResponse(user: User) = UserResponse().apply {
        userId = user.userId
        email = user.email
    }
}