package isla.del.lago.shenglong.controller

import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.request.bill.CreateBillRequest
import isla.del.lago.shenglong.response.bill.BillResponse
import isla.del.lago.shenglong.response.bill.DeleteBillResponse
import isla.del.lago.shenglong.route.Route
import isla.del.lago.shenglong.service.BillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [Route.BASE_PATH + Route.Bill.BASE_PATH])
class BillController {

    @Autowired
    private lateinit var billService: BillService

    @PostMapping
    fun createBill(@Validated @RequestBody body: CreateBillRequest): ResponseEntity<BillResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(billService.createBill(body))

    @GetMapping
    fun getAllBills(): ResponseEntity<List<BillResponse>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(billService.getAllBills())

    @DeleteMapping(Route.Bill.BY_BILL_ID)
    fun deleteBillById(@PathVariable(Constant.PathParam.BILL_ID) billId: Int): ResponseEntity<DeleteBillResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(billService.deleteBillById(billId))
}