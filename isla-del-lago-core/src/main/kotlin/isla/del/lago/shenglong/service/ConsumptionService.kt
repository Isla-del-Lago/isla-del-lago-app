package isla.del.lago.shenglong.service

import isla.del.lago.shenglong.request.consumption.CreateConsumptionsRequest
import isla.del.lago.shenglong.response.ConsumptionDetailResponse

interface ConsumptionService {

    fun addConsumptions(billId: Int, userId: String, createConsumptionsRequest: CreateConsumptionsRequest)

    fun getConsumptionDetails(billId: Int, userId: String, apartmentId: String): ConsumptionDetailResponse
}