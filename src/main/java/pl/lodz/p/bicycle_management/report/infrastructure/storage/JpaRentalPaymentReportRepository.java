package pl.lodz.p.bicycle_management.report.infrastructure.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.report.domain.*;

import java.util.Optional;

public interface JpaRentalPaymentReportRepository extends JpaRepository<RentalPaymentReport, Integer> {
    Page<RentalPaymentReport> findAllByUserId(UserId userId, Pageable pageable);
    Optional<RentalPaymentReport> findByRentalNumber(RentalNumber rentalNumber);
}
