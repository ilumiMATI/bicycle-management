package pl.lodz.p.bicycle_management.report.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class RentalReportService {
    private final RentalReportRepository rentalReportRepository;

    public RentalReport save(RentalReport rentalReport) {
        log.info(prefix() + "Saving...");
        return rentalReportRepository.save(rentalReport);
    }

    public RentalReport findByRentalNumber(RentalNumber rentalNumber) {
        log.info(prefix() + "finding rental report with rental number: " + rentalNumber);
        return rentalReportRepository.findByRentalNumber(rentalNumber)
                .orElseThrow(RentalReportNotFoundException::new);
    }

    public PageRentalReport findAll(Pageable pageable) {
        log.info(prefix() + "finding all rentals");
        return rentalReportRepository.findAll(pageable);
    }

    public PageRentalReport findAllByUserId(UserId userId, Pageable pageable) {
        log.info(prefix() + "finding all rentals with user id: " + userId.value().toString());
        return rentalReportRepository.findAllByUserId(userId, pageable);
    }

    public PageRentalReport findAllByBicycleNumber(BicycleNumber bicycleNumber, Pageable pageable) {
        log.info(prefix() + "finding all rentals with bicycle number: " + bicycleNumber.asString());
        return rentalReportRepository.findAllByBicycleNumber(bicycleNumber, pageable);
    }

    private String prefix() {
        return "[RentalReportService]: ";
    }
}
