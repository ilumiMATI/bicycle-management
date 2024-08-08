package pl.lodz.p.bicycle_management.report.infrastructure.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.report.domain.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public class RentalPaymentReportStorageAdapter implements RentalPaymentReportRepository {
    private final JpaRentalPaymentReportRepository jpaRentalPaymentReportRepository;

    @Override
    public RentalPaymentReport save(RentalPaymentReport rentalPaymentReport) {
        try {
            RentalPaymentReport saved = jpaRentalPaymentReportRepository.save(rentalPaymentReport);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("Rental Payment Report for User " + rentalPaymentReport.getUserId() + " already exits in db");
            throw new RentalPaymentReportAlreadyExistsException();
        }
    }

    @Override
    public Optional<RentalPaymentReport> findByRentalNumber(RentalNumber rentalNumber) {
        return jpaRentalPaymentReportRepository.findByRentalNumber(rentalNumber);
    }

    @Override
    public PageRentalPaymentReport findAll(Pageable pageable) {
        Page<RentalPaymentReport> pageRentalPaymentReports = jpaRentalPaymentReportRepository.findAll(pageable);
        List<RentalPaymentReport> reports = pageRentalPaymentReports.getContent();

        return new PageRentalPaymentReport(
                reports,
                pageable.getPageNumber() + 1,
                pageRentalPaymentReports.getTotalPages(),
                pageRentalPaymentReports.getTotalElements()
        );
    }

    @Override
    public PageRentalPaymentReport findAllByUserId(UserId userId, Pageable pageable) {
        Page<RentalPaymentReport> pageRentalPaymentReports = jpaRentalPaymentReportRepository.findAllByUserId(userId, pageable);
        List<RentalPaymentReport> reports = pageRentalPaymentReports.getContent();

        return new PageRentalPaymentReport(
                reports,
                pageable.getPageNumber() + 1,
                pageRentalPaymentReports.getTotalPages(),
                pageRentalPaymentReports.getTotalElements()
        );
    }
}
