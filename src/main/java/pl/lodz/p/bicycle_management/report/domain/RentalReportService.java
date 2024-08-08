package pl.lodz.p.bicycle_management.report.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalReportService {
    private final RentalReportRepository rentalReportRepository;

    public RentalReport save(RentalReport rentalReport) {
        return rentalReportRepository.save(rentalReport);
    }

    public RentalReport findByRentalNumber(RentalNumber rentalNumber) {
        return rentalReportRepository.findByRentalNumber(rentalNumber)
                .orElseThrow(RentalReportNotFoundException::new);
    }

    public PageRentalReport findAll(Pageable pageable) {
        return rentalReportRepository.findAll(pageable);
    }

    public PageRentalReport findAllByUserId(UserId userId, Pageable pageable) {
        return rentalReportRepository.findAllByUserId(userId, pageable);
    }

    public PageRentalReport findAllByBicycleNumber(BicycleNumber bicycleNumber, Pageable pageable) {
        return rentalReportRepository.findAllByBicycleNumber(bicycleNumber, pageable);
    }
}
