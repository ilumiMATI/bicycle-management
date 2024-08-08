package pl.lodz.p.bicycle_management.report.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public class RentalPaymentReportService {
    private final RentalPaymentReportRepository rentalPaymentReportRepository;

    public RentalPaymentReport save(RentalPaymentReport rentalPaymentReport) {
        log.info(prefix() + "Saving " + rentalPaymentReport.rentalNumber);
        return rentalPaymentReportRepository.save(rentalPaymentReport);
    }

    public RentalPaymentReport findByRentalNumber(RentalNumber rentalNumber) {
        log.info(prefix() + "Finding by " + rentalNumber.asString());
        return rentalPaymentReportRepository.findByRentalNumber(rentalNumber)
                .orElseThrow(RentalPaymentReportNotFoundException::new);
    }

    public PageRentalPaymentReport findAll(Pageable pageable) {
        log.info(prefix() + "Finding all");
        return rentalPaymentReportRepository.findAll(pageable);
    }

    public PageRentalPaymentReport findAllByUserId(UserId userId, Pageable pageable) {
        log.info(prefix() + "Finding all by userId " + userId.asString());
        return rentalPaymentReportRepository.findAllByUserId(userId, pageable);
    }

    private String prefix() {
        return "[RentalPaymentReportService] ";
    }
}
