package isla.del.lago.shenglong.environment

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class ShenglongEnvironment {

    companion object {
        var jwtSignatureSecret: String? = null
    }

    @Value("\${jwt.signature.secret}")
    fun setJwtSignatureSecret(environmentKey: String) {
        jwtSignatureSecret = environmentKey
    }
}