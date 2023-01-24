package isla.del.lago.shenglong.enums

import isla.del.lago.shenglong.route.Route
import org.springframework.http.HttpMethod

enum class RoleEnum {
    ROOT, ADMIN, USER;

    companion object {

        private val userPaths = hashMapOf(
            HttpMethod.POST.name to arrayOf(
                Route.BASE_PATH + Route.Security.BASE_PATH + Route.Security.Token.REFRESH
            ),
            HttpMethod.GET.name to arrayOf(
                Route.BASE_PATH + Route.Bill.BASE_PATH,
                Route.BASE_PATH + Route.User.BASE_PATH + Route.User.BY_USER_ID,
                Route.BASE_PATH + Route.Consumption.BASE_PATH + Route.Consumption.CONSUMPTION_DETAILS,
                Route.BASE_PATH + Route.Consumption.BASE_PATH + Route.Consumption.CONSUMPTION_DETAILS_BY_APARTMENT_ID
            )
        )

        private val adminPaths = hashMapOf(
            HttpMethod.GET.name to arrayOf(
                Route.BASE_PATH + Route.User.BASE_PATH
            ) + userPaths[HttpMethod.GET.name].orEmpty(),
            HttpMethod.POST.name to arrayOf("") + userPaths[HttpMethod.POST.name].orEmpty()
        )

        fun validateUserPaths(httpMethod: String, path: String) = userPaths[httpMethod]?.contains(path)

        fun validateAdminPaths(httpMethod: String, path: String) = adminPaths[httpMethod]?.contains(path)!!
    }
}