package isla.del.lago.shenglong.service

import isla.del.lago.shenglong.request.consumption.CreateConsumptionsRequest

interface ConsumptionService {

    fun addConsumptions(billId: Int, userId: String, createConsumptionsRequest: CreateConsumptionsRequest)
}