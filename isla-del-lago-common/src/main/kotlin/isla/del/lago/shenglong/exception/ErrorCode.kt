package isla.del.lago.shenglong.exception

object ErrorCode {
    const val INVALID_REQUEST = "invalid_request"
    const val USER_NOT_FOUND = "user_not_found"
    const val INVALID_LOGIN_CREDENTIALS = "invalid_login_credentials"
    const val INVALID_TOKEN = "invalid_token"
    const val CONSUMPTIONS_EXISTS_FOR_BILL_ID = "consumptions_exists_for_bill_id"
    const val CONSUMPTIONS_NOT_CREATED = "consumptions_not_created"
    const val BILL_DOES_NOT_EXISTS = "the_bill_does_not_exists"
    const val BILL_ALREADY_EXISTS = "bill_already_exists"
    const val MISSING_AUTH_TOKEN = "missing_auth_token"
    const val USER_EXISTS = "user_exists"
    const val UNAUTHORIZED_FLOW = "unauthorized_flow"
}