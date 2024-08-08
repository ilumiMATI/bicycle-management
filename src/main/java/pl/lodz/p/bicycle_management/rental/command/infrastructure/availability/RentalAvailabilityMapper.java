package pl.lodz.p.bicycle_management.rental.command.infrastructure.availability;

import org.mapstruct.Mapper;
import pl.lodz.p.bicycle_management.rental.command.domain.RentDuration;

@Mapper(componentModel = "spring")
public interface RentalAvailabilityMapper {

    RentDuration toRentalContext(pl.lodz.p.bicycle_management.availability.command.domain.LockDuration lockDuration);

}
