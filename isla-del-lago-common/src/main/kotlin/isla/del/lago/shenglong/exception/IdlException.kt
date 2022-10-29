package isla.del.lago.shenglong.exception

import org.springframework.http.HttpStatus

class IdlException(
    val code: String,
    override val message: String,
    val status: HttpStatus,
    override val cause: Throwable? = null
) : RuntimeException()