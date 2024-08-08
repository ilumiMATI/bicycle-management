package pl.lodz.p.bicycle_management.report.infrastructure.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsAlreadyExistsException;
import pl.lodz.p.bicycle_management.report.domain.*;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log
public class RentalReportStorageAdapter implements RentalReportRepository {
    private final JpaRentalReportRepository jpaRentalReportRepository;

    @Override
    public RentalReport save(RentalReport rentalReport) {
        try {
            RentalReport saved = jpaRentalReportRepository.save(rentalReport);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("Rental Report for User " + rentalReport.getUserId() + " already exits in db");
            throw new RentalReportAlreadyExistsException();
        }
    }

    @Override
    public Optional<RentalReport> findByRentalNumber(RentalNumber rentalNumber) {
        return jpaRentalReportRepository.findByRentalNumber(rentalNumber);
    }

    @Override
    public PageRentalReport findAll(Pageable pageable) {
        Page<RentalReport> rentalReportPage = jpaRentalReportRepository.findAll(pageable);
        List<RentalReport> rentalReportList = rentalReportPage.getContent();

        return new PageRentalReport(
                rentalReportList,
                pageable.getPageNumber() + 1,
                rentalReportPage.getTotalPages(),
                rentalReportPage.getTotalElements()
        );
    }

    @Override
    public PageRentalReport findAllByUserId(UserId userId, Pageable pageable) {
        Page<RentalReport> rentalReportPage = jpaRentalReportRepository.findAllByUserId(userId, pageable);
        List<RentalReport> rentalReportList = rentalReportPage.getContent();

        return new PageRentalReport(
                rentalReportList,
                pageable.getPageNumber() + 1,
                rentalReportPage.getTotalPages(),
                rentalReportPage.getTotalElements()
        );
    }

    @Override
    public PageRentalReport findAllByBicycleNumber(BicycleNumber bicycleNumber, Pageable pageable) {
        Page<RentalReport> rentalReportPage = jpaRentalReportRepository.findAllByBicycleNumber(bicycleNumber, pageable);
        List<RentalReport> rentalReportList = rentalReportPage.getContent();

        return new PageRentalReport(
                rentalReportList,
                pageable.getPageNumber() + 1,
                rentalReportPage.getTotalPages(),
                rentalReportPage.getTotalElements()
        );
    }

}
