package pl.lodz.p.bicycle_management.report.infrastructure.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.report.domain.*;

import java.util.Optional;

public interface JpaRentalReportRepository extends JpaRepository<RentalReport, Integer> {
    Page<RentalReport> findAllByUserId(UserId userId, Pageable pageable);
    Page<RentalReport> findAllByBicycleNumber(BicycleNumber bicycleNumber, Pageable pageable);
    Optional<RentalReport> findByRentalNumber(RentalNumber rentalNumber);
}
