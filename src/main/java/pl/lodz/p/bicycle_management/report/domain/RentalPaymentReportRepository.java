package pl.lodz.p.bicycle_management.report.domain;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RentalPaymentReportRepository {
    RentalPaymentReport save(RentalPaymentReport rentalPaymentReport);
    Optional<RentalPaymentReport> findByRentalNumber(RentalNumber rentalNumber);
    PageRentalPaymentReport findAll(Pageable pageable);
    PageRentalPaymentReport findAllByUserId(UserId userId, Pageable pageable);
}
