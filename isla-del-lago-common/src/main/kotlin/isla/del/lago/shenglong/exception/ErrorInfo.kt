package isla.del.lago.shenglong.exception

import isla.del.lago.shenglong.exception.ErrorCode.BILL_ALREADY_EXISTS
import isla.del.lago.shenglong.exception.ErrorCode.BILL_DOES_NOT_EXISTS
import isla.del.lago.shenglong.exception.ErrorCode.CONSUMPTIONS_EXISTS_FOR_BILL_ID
import isla.del.lago.shenglong.exception.ErrorCode.CONSUMPTIONS_NOT_CREATED
import isla.del.lago.shenglong.exception.ErrorCode.INVALID_LOGIN_CREDENTIALS
import isla.del.lago.shenglong.exception.ErrorCode.INVALID_REQUEST
import isla.del.lago.shenglong.exception.ErrorCode.INVALID_TOKEN
import isla.del.lago.shenglong.exception.ErrorCode.MISSING_AUTH_TOKEN
import isla.del.lago.shenglong.exception.ErrorCode.UNAUTHORIZED_FLOW
import isla.del.lago.shenglong.exception.ErrorCode.USER_EXISTS
import isla.del.lago.shenglong.exception.ErrorCode.USER_NOT_FOUND
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class ErrorInfo(
    private val errorCode: String,
    private val errorMessage: String,
    private val httpStatus: HttpStatus
) {

    ERROR_INVALID_LOGIN_CREDENTIALS(INVALID_LOGIN_CREDENTIALS, INVALID_LOGIN_CREDENTIALS, BAD_REQUEST),
    ERROR_INVALID_REQUEST(INVALID_REQUEST, INVALID_REQUEST, BAD_REQUEST),
    ERROR_INVALID_TOKEN(INVALID_TOKEN, INVALID_TOKEN, UNAUTHORIZED),
    ERROR_CONSUMPTIONS_EXISTS_FOR_BILL_ID(CONSUMPTIONS_EXISTS_FOR_BILL_ID, CONSUMPTIONS_EXISTS_FOR_BILL_ID, CONFLICT),
    ERROR_CONSUMPTIONS_NOT_CREATED(CONSUMPTIONS_NOT_CREATED, CONSUMPTIONS_NOT_CREATED, BAD_REQUEST),
    ERROR_BILL_DOES_NOT_EXISTS(BILL_DOES_NOT_EXISTS, BILL_DOES_NOT_EXISTS, BAD_REQUEST),
    ERROR_BILL_ALREADY_EXISTS(BILL_ALREADY_EXISTS, BILL_ALREADY_EXISTS, CONFLICT),
    ERROR_MISSING_AUTH_TOKEN(MISSING_AUTH_TOKEN, MISSING_AUTH_TOKEN, BAD_REQUEST),
    ERROR_USERS_EXISTS(USER_EXISTS, USER_EXISTS, CONFLICT),
    ERROR_USER_NOT_FOUND(USER_NOT_FOUND, USER_NOT_FOUND, BAD_REQUEST),
    ERROR_UNAUTHORIZED_FLOW(UNAUTHORIZED_FLOW, UNAUTHORIZED_FLOW, FORBIDDEN);

    fun buildIdlException(): IdlException = IdlException(errorCode, errorMessage, httpStatus)
}