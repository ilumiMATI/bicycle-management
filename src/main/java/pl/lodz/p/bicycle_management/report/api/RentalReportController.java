package pl.lodz.p.bicycle_management.report.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.bicycle_management.report.domain.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/reports/rentals",
        produces = "application/json",
        consumes = "application/json"
)
public class RentalReportController {

    private final RentalReportService rentalReportService;
    private final PageRentalReportDtoMapper pageRentalReportDtoMapper;
    private final RentalReportDtoMapper rentalReportDtoMapper;

    @GetMapping(path = "/{rentalNumber}")
    public ResponseEntity<RentalReportDto> getRentalReport(@PathVariable String rentalNumber) {
        RentalReport rentalReport = rentalReportService.findByRentalNumber(RentalNumber.of(rentalNumber));
        return ResponseEntity.ok(rentalReportDtoMapper.toDto(rentalReport));
    }

    @GetMapping
    public ResponseEntity<PageRentalReportDto> getAllReports(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                pageRentalReportDtoMapper.toPageDto(rentalReportService.findAll(pageable))
        );
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<PageRentalReportDto> getAllReportsWithUserId(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    )
    {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                pageRentalReportDtoMapper.toPageDto(rentalReportService.findAllByUserId(UserId.of(userId),pageable))
        );
    }

    @GetMapping(path = "/bicycle/{bicycleNumber}")
    public ResponseEntity<PageRentalReportDto> getAllReportsWithUserId(
            @PathVariable String bicycleNumber,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    )
    {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                pageRentalReportDtoMapper.toPageDto(
                        rentalReportService.findAllByBicycleNumber(BicycleNumber.of(bicycleNumber),pageable)
                )
        );
    }
}
