package isla.del.lago.shenglong.enums

import isla.del.lago.shenglong.response.apartment.ApartmentInfoResponse

enum class ApartmentEnum(private val apartmentId: String, private val apartmentName: String) {

    APARTMENT_201("apartamento_201", "Apartamento 201"),
    APARTMENT_202("apartamento_202", "Apartamento 202"),
    APARTMENT_301("apartamento_301", "Apartamento 301"),
    APARTMENT_302("apartamento_302", "Apartamento 302"),
    APARTMENT_401("apartamento_401", "Apartamento 401"),
    APARTMENT_402("apartamento_402", "Apartamento 402"),
    APARTMENT_501("apartamento_501", "Apartamento 501"),
    APARTMENT_502("apartamento_502", "Apartamento 502"),
    LOCAL_1("local_1", "Local 1"),
    LOCAL_2("local_2", "Local 2"),
    TOTAL("total", "total");

    companion object {
        fun getApartmentsInfo() = ApartmentEnum.values()
            .map {
                ApartmentInfoResponse().apply {
                    this.apartmentId = it.apartmentId; this.apartmentName = it.apartmentName
                }
            }
    }
}