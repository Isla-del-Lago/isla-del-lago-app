package isla.del.lago.shenglong.route

import isla.del.lago.shenglong.Constant

object Route {

    private const val API_V1 = "api/v1"
    const val BASE_PATH = "/isla-del-lago/$API_V1"

    object Health {
        const val BASE_PATH = "/health"
    }

    object Security {
        const val BASE_PATH = "/security"
        const val LOGIN = "/login"

        object Token {
            private const val BASE_PATH = "/token"
            const val VALIDATE = "/validate"
            const val CREATE = "/create"
            const val REFRESH = "/refresh"
        }
    }

    object Bill {
        const val BASE_PATH = "/bill"
        const val BY_BILL_ID = "/{${Constant.PathParam.BILL_ID}}"
    }

    object User {
        const val BASE_PATH = "/user"
        const val BY_USER_ID = "/{${Constant.PathParam.USER_ID}}"
    }

    object Consumption {
        const val BASE_PATH = "/consumption"
        const val CONSUMPTION_DETAILS = "/details"
    }
}