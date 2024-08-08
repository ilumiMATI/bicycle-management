package pl.lodz.p.bicycle_management.rental.command.infrastructure.report;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.rental.command.domain.BicycleNumber;
import pl.lodz.p.bicycle_management.rental.command.domain.RentalNumber;
import pl.lodz.p.bicycle_management.rental.command.domain.UserId;
import pl.lodz.p.bicycle_management.rental.query.facade.BicycleNumberMappingHelper;

@Mapper(componentModel = "spring")
public interface RentalReportServiceMapper extends BicycleNumberMappingHelper {
    UserId toRentalContext(pl.lodz.p.bicycle_management.report.domain.UserId userId);
    BicycleNumber toRentalContext(pl.lodz.p.bicycle_management.report.domain.BicycleNumber bicycleNumber);
    RentalNumber toRentalContext(pl.lodz.p.bicycle_management.report.domain.RentalNumber rentalNumber);

    pl.lodz.p.bicycle_management.report.domain.UserId toReportContext(UserId userId);
    pl.lodz.p.bicycle_management.report.domain.BicycleNumber toReportContext(BicycleNumber bicycleNumber);
    pl.lodz.p.bicycle_management.report.domain.RentalNumber toReportContext(RentalNumber rentalNumber);
}
