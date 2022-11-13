package isla.del.lago.shenglong.service

import isla.del.lago.shenglong.response.apartment.ApartmentInfoResponse

interface ApartmentService {

    fun getApartments(userId: String): List<ApartmentInfoResponse>
}