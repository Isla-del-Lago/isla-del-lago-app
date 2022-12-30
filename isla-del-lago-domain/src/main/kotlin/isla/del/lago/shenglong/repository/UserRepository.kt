package isla.del.lago.shenglong.repository

import isla.del.lago.shenglong.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun findByEmail(email: String): User?

    fun findUserByUserId(userId: String): User?
}