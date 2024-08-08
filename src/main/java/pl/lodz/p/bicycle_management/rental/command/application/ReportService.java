package pl.lodz.p.bicycle_management.rental.command.application;

import pl.lodz.p.bicycle_management.rental.command.domain.BicycleNumber;
import pl.lodz.p.bicycle_management.rental.command.domain.UserId;
import pl.lodz.p.bicycle_management.rental.command.domain.RentalNumber;

import java.time.LocalDateTime;

public interface ReportService {
    RentalNumber save(UserId userId, BicycleNumber bicycleNumber, LocalDateTime startTime, LocalDateTime endTime);
}
