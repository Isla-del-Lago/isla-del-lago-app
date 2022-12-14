package isla.del.lago.shenglong.repository

import isla.del.lago.shenglong.model.Bill
import org.springframework.data.jpa.repository.JpaRepository

interface BillRepository : JpaRepository<Bill, Int> {

    fun existsByStartDateOrEndDate(startDate: String, endDate: String): Boolean
}