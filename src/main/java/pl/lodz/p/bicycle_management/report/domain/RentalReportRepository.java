package pl.lodz.p.bicycle_management.report.domain;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RentalReportRepository {
    RentalReport save(RentalReport rentalReport);
    Optional<RentalReport> findByRentalNumber(RentalNumber rentalNumber);
    PageRentalReport findAll(Pageable pageable);
    PageRentalReport findAllByUserId(UserId userId, Pageable pageable);
    PageRentalReport findAllByBicycleNumber(BicycleNumber bicycleNumber, Pageable pageable);
}
