package isla.del.lago.shenglong.exception

import isla.del.lago.shenglong.exception.ErrorCode.BILL_DOES_NOT_EXISTS
import isla.del.lago.shenglong.exception.ErrorCode.CONSUMPTIONS_EXISTS_FOR_BILL_ID
import isla.del.lago.shenglong.exception.ErrorCode.CONSUMPTIONS_NOT_CREATED
import isla.del.lago.shenglong.exception.ErrorCode.INVALID_LOGIN_CREDENTIALS
import isla.del.lago.shenglong.exception.ErrorCode.INVALID_REQUEST
import isla.del.lago.shenglong.exception.ErrorCode.INVALID_TOKEN
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CONFLICT

enum class ErrorInfo(
    private val errorCode: String,
    private val errorMessage: String,
    private val httpStatus: HttpStatus
) {

    ERROR_INVALID_LOGIN_CREDENTIALS(INVALID_LOGIN_CREDENTIALS, INVALID_LOGIN_CREDENTIALS, BAD_REQUEST),
    ERROR_INVALID_REQUEST(INVALID_REQUEST, INVALID_REQUEST, BAD_REQUEST),
    ERROR_INVALID_TOKEN(INVALID_TOKEN, INVALID_TOKEN, BAD_REQUEST),
    ERROR_CONSUMPTIONS_EXISTS_FOR_BILL_ID(CONSUMPTIONS_EXISTS_FOR_BILL_ID, CONSUMPTIONS_EXISTS_FOR_BILL_ID, CONFLICT),
    ERROR_CONSUMPTIONS_NOT_CREATED(CONSUMPTIONS_NOT_CREATED, CONSUMPTIONS_NOT_CREATED, BAD_REQUEST),
    ERROR_BILL_DOES_NOT_EXISTS(BILL_DOES_NOT_EXISTS, BILL_DOES_NOT_EXISTS, BAD_REQUEST);

    fun buildIdlException(): IdlException = IdlException(errorCode, errorMessage, httpStatus)
}