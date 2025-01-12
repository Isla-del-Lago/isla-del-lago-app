package isla.del.lago.shenglong

object Constant {

    const val SPACE_SEPARATOR = " "
    const val TOTAL = "total"

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
            const val USER_NAME_CLAIM = "user-name"
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
        const val APARTMENT_REGEX = "(^apartamento_[2-5]0[1-2]\$|^local_[1-2]\$|^total\$)"
    }

    object Consumption {
        const val MAX_CONSUMPTION_SIZE = 10
    }

    object Apartment {
        const val APARTMENT_201 = "apartamento_201"
        const val APARTMENT_202 = "apartamento_202"
        const val APARTMENT_301 = "apartamento_301"
        const val APARTMENT_302 = "apartamento_302"
        const val APARTMENT_401 = "apartamento_401"
        const val APARTMENT_402 = "apartamento_402"
        const val APARTMENT_501 = "apartamento_501"
        const val APARTMENT_502 = "apartamento_502"
        const val LOCAL_1 = "local_1"
        const val LOCAL_2 = "local_2"

        val ALL = listOf(
            APARTMENT_201, APARTMENT_202, APARTMENT_301,
            APARTMENT_302, APARTMENT_401, APARTMENT_402,
            APARTMENT_501, APARTMENT_502, LOCAL_1, LOCAL_2
        )
    }
}