package pl.lodz.p.bicycle_management.report.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.report.domain.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/reports/rentals/payments",
        produces = "application/json",
        consumes = "application/json"
)
public class RentalPaymentReportController {

    private final RentalPaymentReportService rentalPaymentReportService;
    private final RentalPaymentReportDtoMapper mapper;
    private final PageRentalPaymentReportDtoMapper pageMapper;

    @GetMapping(path = "/{rentalNumber}")
    public ResponseEntity<RentalPaymentReportDto> findByRentalNumber(@PathVariable String rentalNumber) {
        RentalPaymentReport rentalPaymentReport = rentalPaymentReportService.findByRentalNumber(RentalNumber.of(rentalNumber));
        return ResponseEntity.ok(mapper.toDto(rentalPaymentReport));
    }

    @GetMapping
    public ResponseEntity<PageRentalPaymentReportDto> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(pageMapper.toPageDto(rentalPaymentReportService.findAll(pageable)));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<PageRentalPaymentReportDto> findAllByUserId(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(pageMapper.toPageDto(rentalPaymentReportService.findAllByUserId(UserId.of(userId),pageable)));
    }
}
