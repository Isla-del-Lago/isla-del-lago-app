package isla.del.lago.shenglong.repository

import isla.del.lago.shenglong.model.Consumption
import org.springframework.data.jpa.repository.JpaRepository

interface ConsumptionRepository : JpaRepository<Consumption, Int> {
    fun findAllByBillId(billId: Int): List<Consumption>

    fun findByBillIdAndApartmentId(billId: Int, apartmentId: String): Consumption?

    fun deleteAllByBillId(billId: Int)
}