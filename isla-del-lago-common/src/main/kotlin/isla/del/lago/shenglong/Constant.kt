package isla.del.lago.shenglong

object Constant {

    const val SPACE_SEPARATOR = " "

    object PathParam {
        const val BILL_ID = "bill-id"
        const val USER_ID = "user-id"
        const val APARTMENT_ID = "apartment-id"
    }

    object Jwt {
        const val ISSUER = "isla.del.lago"
        const val EXPIRATION_TIME = 5 * 60 * 1000

        object Claims {
            const val USER_ID_CLAIM = "user-id"
            const val EMAIL_CLAIM = "email"
            const val USER_ROLE_CLAIM = "role"
        }
    }

    object Header {
        const val USER_ID = "user-id"
    }

    object QueryParam {
        const val BILL_ID = "bill_id"
        const val APARTMENT_ID = "apartment_id"
    }

    object Regex {
        const val APARTMENT_REGEX = "(^apartamento_[2-5]0[1-2]\$|^local_[1-2]\$)"
    }

    object Consumption {
        const val MAX_CONSUMPTION_SIZE = 10
    }
}