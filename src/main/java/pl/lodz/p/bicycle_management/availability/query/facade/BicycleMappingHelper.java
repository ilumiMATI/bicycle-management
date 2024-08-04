package pl.lodz.p.bicycle_management.availability.query.facade;

import pl.lodz.p.bicycle_management.availability.command.domain.BicycleId;
import pl.lodz.p.bicycle_management.availability.command.domain.UserId;

public interface BicycleMappingHelper {

    default String map(BicycleId value) {
        return value != null ? value.asString() : null;
    }

    default Integer map(UserId value) {
        return value != null ? value.userId() : null;
    }
}
