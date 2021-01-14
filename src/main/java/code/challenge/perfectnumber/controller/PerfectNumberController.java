package code.challenge.perfectnumber.controller;

import code.challenge.perfectnumber.service.PerfectNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("perfect-numbers")
@Validated
public class PerfectNumberController {
    private final PerfectNumberService perfectNumberService;

    public PerfectNumberController(PerfectNumberService perfectNumberService) {
        this.perfectNumberService = perfectNumberService;
    }

    @GetMapping(path="/check/{number}", produces = "application/json")
    public ResponseEntity<Boolean> checkIsNumberPerfect(@PathVariable Integer number) {
        return ResponseEntity.status(HttpStatus.OK).body(perfectNumberService.isNumberPerfect(number));
    }

    @GetMapping(path="/find-numbers-in-range/{start}/{end}", produces = "application/json")
    public ResponseEntity<List<Integer>> findAllPerfectNumbersInRange(@PathVariable
                                                      @Min(value = 1, message = "id must be greater than or equal to 1")
                                                      @Max(value = Integer.MAX_VALUE,
                                                              message = "id must be lower than or equal to 2147483647")
                                                                  Integer start,
                                                      @PathVariable
                                                      @Min(value = 1, message = "id must be greater than or equal to 1")
                                                      @Max(value = Integer.MAX_VALUE,
                                                              message = "id must be lower than or equal to 2147483647")
                                                              Integer end) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(perfectNumberService.findAllPerfectNumbersInRange(start, end));

    }
}
