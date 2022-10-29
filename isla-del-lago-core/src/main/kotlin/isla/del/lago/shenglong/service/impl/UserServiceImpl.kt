package isla.del.lago.shenglong.service.impl

import isla.del.lago.shenglong.extensions.objectToJson
import isla.del.lago.shenglong.mapper.UserMapper
import isla.del.lago.shenglong.repository.UserRepository
import isla.del.lago.shenglong.request.user.CreateUserRequest
import isla.del.lago.shenglong.response.user.DeleteUserResponse
import isla.del.lago.shenglong.response.user.UserResponse
import isla.del.lago.shenglong.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    companion object {
        private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun createUser(createUserRequest: CreateUserRequest): UserResponse {
        logger.info("--UserServiceImpl:CreateUser --UserInfo:[{}]", createUserRequest.objectToJson())

        return userRepository.save(UserMapper.mapToSaveUser(createUserRequest)).let {
            UserMapper.mapToUserResponse(it)
        }
    }

    override fun getAllUsers(): List<UserResponse> {
        logger.info("--UserServiceImpl:GetAllUsers")

        return userRepository.findAll().map { user -> UserMapper.mapToUserResponse(user) }
    }

    override fun deleteUserById(userId: Int): DeleteUserResponse {
        logger.info("--UserServiceImpl:DeleteUserById --UserId:[{}]", userId)

        userRepository.deleteById(userId)

        return DeleteUserResponse().apply { this.userId = userId }
    }
}