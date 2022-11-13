package isla.del.lago.shenglong.controller

import isla.del.lago.shenglong.Constant
import isla.del.lago.shenglong.route.Route
import isla.del.lago.shenglong.service.ApartmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping(value = [Route.BASE_PATH + Route.Apartment.BASE_PATH])
@CrossOrigin("*")
class ApartmentController(private var apartmentService: ApartmentService) {

    @GetMapping
    fun getApartments(
        @NotBlank @RequestHeader(Constant.Header.USER_ID) userId: String
    ) =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(apartmentService.getApartments(userId))
}