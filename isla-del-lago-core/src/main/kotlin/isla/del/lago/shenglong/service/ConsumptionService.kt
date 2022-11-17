package isla.del.lago.shenglong.service

import isla.del.lago.shenglong.request.consumption.CreateConsumptionsRequest
import isla.del.lago.shenglong.response.consumption.ConsumptionDetailByApartmentResponse
import isla.del.lago.shenglong.response.consumption.ConsumptionDetailResponse

interface ConsumptionService {

    fun addConsumptions(billId: Int, userId: String, createConsumptionsRequest: CreateConsumptionsRequest)

    fun getConsumptionDetails(billId: Int, userId: String, apartmentId: String): ConsumptionDetailResponse

    fun getConsumptionDetailsByApartmentId(userId: String, apartmentId: String): List<ConsumptionDetailByApartmentResponse>
}