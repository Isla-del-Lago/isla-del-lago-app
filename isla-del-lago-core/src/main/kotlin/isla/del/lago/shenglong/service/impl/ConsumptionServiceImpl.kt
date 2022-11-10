package isla.del.lago.shenglong.service.impl

import isla.del.lago.shenglong.request.consumption.CreateConsumptionsRequest
import isla.del.lago.shenglong.service.ConsumptionService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ConsumptionServiceImpl : ConsumptionService {

    companion object {
        private val logger = LoggerFactory.getLogger(ConsumptionServiceImpl::class.java)
    }

    override fun addConsumptions(billId: Int, userId: String, createConsumptionsRequest: CreateConsumptionsRequest) {
        TODO("Not yet implemented")
    }
}