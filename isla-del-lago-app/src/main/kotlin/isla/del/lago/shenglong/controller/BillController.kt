package isla.del.lago.shenglong.controller

import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.request.bill.CreateBillRequest
import isla.del.lago.shenglong.response.bill.BillResponse
import isla.del.lago.shenglong.route.Route
import isla.del.lago.shenglong.service.BillService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping(value = [Route.BASE_PATH + Route.Bill.BASE_PATH])
@CrossOrigin("*")
class BillController {

    @Autowired
    private lateinit var billService: BillService

    @PostMapping
    fun createBill(
        @NotBlank @RequestHeader(Constant.Header.USER_ID) userId: String,
        @Valid @RequestBody body: CreateBillRequest
    ) =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(billService.createBill(userId, body))

    @GetMapping
    fun getAllBills(): ResponseEntity<List<BillResponse>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(billService.getAllBills())

    @DeleteMapping(Route.Bill.BY_BILL_ID)
    fun deleteBillById(@PathVariable(Constant.PathParam.BILL_ID) billId: Int) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(billService.deleteBillById(billId))
}