package isla.del.lago.shenglong.service.impl

import isla.del.lago.shenglong.exception.ErrorInfo
import isla.del.lago.shenglong.mapper.UserMapper
import isla.del.lago.shenglong.repository.UserRepository
import isla.del.lago.shenglong.request.user.CreateUserRequest
import isla.del.lago.shenglong.response.user.DeleteUserResponse
import isla.del.lago.shenglong.response.user.UserResponse
import isla.del.lago.shenglong.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException

@Service
class UserServiceImpl : UserService {

    companion object {
        private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun createUser(createUserRequest: CreateUserRequest): UserResponse {
        logger.info("--UserServiceImpl:CreateUser --UserEmail:[{}]", createUserRequest.email)

        return try {
            userRepository.save(UserMapper.mapToSaveUser(createUserRequest)).let {
                UserMapper.mapToUserResponse(it)
            }
        } catch (ex: Exception) {
            logger.error(
                "---UserServiceImpl:CreateUser --Error Creating User --UserEmail:[{}] --Exception:[{}]",
                createUserRequest.email, ex.message
            )
            throw ErrorInfo.ERROR_USERS_EXISTS.buildIdlException()
        }
    }

    override fun getAllUsers(): List<UserResponse> {
        logger.info("--UserServiceImpl:GetAllUsers")

        return userRepository.findAll().map { user -> UserMapper.mapToUserResponse(user) }
    }

    override fun deleteUserById(userId: String): DeleteUserResponse {
        logger.info("--UserServiceImpl:DeleteUserById --UserId:[{}]", userId)

        userRepository.deleteById(userId)

        return DeleteUserResponse().apply { this.userId = userId }
    }

    override fun getUserById(userId: String): UserResponse {
        logger.info("--UserServiceImpl:GetUserById --UserId:[{}]", userId)

        userRepository.findUserByUserId(userId)?.let {
            return UserMapper.mapToUserResponse(it)
        } ?: run {
            logger.error("--UserServiceImpl:GetUserById --UserId:[{}] --User Not Found", userId)

            throw ErrorInfo.ERROR_USER_NOT_FOUND.buildIdlException()
        }
    }
}