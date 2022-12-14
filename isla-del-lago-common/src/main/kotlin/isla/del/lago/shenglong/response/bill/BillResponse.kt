package isla.del.lago.shenglong.response.bill

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class BillResponse {
    var billId: Int? = null
    var startDate: String? = null
    var endDate: String? = null
}