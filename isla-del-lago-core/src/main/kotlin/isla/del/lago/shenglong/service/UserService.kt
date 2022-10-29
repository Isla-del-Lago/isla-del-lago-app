package isla.del.lago.shenglong.service

import isla.del.lago.shenglong.request.user.CreateUserRequest
import isla.del.lago.shenglong.response.user.DeleteUserResponse
import isla.del.lago.shenglong.response.user.UserResponse

interface UserService {

    fun createUser(createUserRequest: CreateUserRequest): UserResponse

    fun getAllUsers(): List<UserResponse>

    fun deleteUserById(userId: Int): DeleteUserResponse
}