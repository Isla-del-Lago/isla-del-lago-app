package isla.del.lago.shenglong.handler

import isla.del.lago.shenglong.exception.ErrorCode
import isla.del.lago.shenglong.exception.IdlException
import isla.del.lago.shenglong.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptionHandler {

    companion object {
        private val logger = LoggerFactory.getLogger(CustomExceptionHandler::class.java)
    }

    @ExceptionHandler
    fun handleIdlException(ex: IdlException) =
        ResponseEntity.status(ex.status).body(ErrorResponse().apply { code = ex.code; message = ex.message })

    @ExceptionHandler
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        logger.error(
            "--CustomExceptionHandler:HandleMethodArgumentNotValidException --Exception:[{}]",
            ex.message
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse().apply {
            code = ErrorCode.INVALID_REQUEST
            message = ErrorCode.INVALID_REQUEST
        })
    }
}