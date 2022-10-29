package isla.del.lago.shenglong

object Constant {

    object PathParam {
        const val BILL_ID = "bill-id"
        const val USER_ID = "user-id"
    }

    object Jwt {
        const val ISSUER = "isla.del.lago"
        const val EXPIRATION_TIME = 5 * 60 * 1000

        object Claims {
            const val USER_ID_CLAIM = "user-id"
            const val EMAIL_CLAIM = "email"
        }
    }
}