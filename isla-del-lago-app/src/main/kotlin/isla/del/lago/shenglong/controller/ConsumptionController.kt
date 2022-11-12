package isla.del.lago.shenglong.controller

import isla.del.lago.shenglong.Constant.Header
import isla.del.lago.shenglong.Constant.QueryParam
import isla.del.lago.shenglong.request.consumption.CreateConsumptionsRequest
import isla.del.lago.shenglong.route.Route
import isla.del.lago.shenglong.service.ConsumptionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@RestController
@RequestMapping(value = [Route.BASE_PATH + Route.Consumption.BASE_PATH])
@CrossOrigin("*")
@Validated
class ConsumptionController(private val consumptionService: ConsumptionService) {

    @PostMapping
    fun addConsumptions(
        @NotBlank @RequestHeader(Header.USER_ID) userId: String,
        @Positive @RequestParam(QueryParam.BILL_ID) billId: Int,
        @RequestBody @Valid body: CreateConsumptionsRequest
    ) =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(consumptionService.addConsumptions(billId, userId, body))
}