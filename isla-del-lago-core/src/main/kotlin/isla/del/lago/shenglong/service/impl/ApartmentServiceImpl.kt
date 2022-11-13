package isla.del.lago.shenglong.service.impl

import isla.del.lago.shenglong.enums.ApartmentEnum
import isla.del.lago.shenglong.response.apartment.ApartmentInfoResponse
import isla.del.lago.shenglong.service.ApartmentService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ApartmentServiceImpl : ApartmentService {

    companion object {
        private val logger = LoggerFactory.getLogger(ApartmentServiceImpl::class.java)
    }

    override fun getApartments(userId: String): List<ApartmentInfoResponse> {
        logger.info(
            "--ApartmentServiceImpl:GetApartments --UserId:[{}]",
            userId
        )

        return ApartmentEnum.getApartmentsInfo()
    }
}