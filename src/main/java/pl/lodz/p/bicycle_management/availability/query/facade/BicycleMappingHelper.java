package pl.lodz.p.bicycle_management.availability.query.facade;

import pl.lodz.p.bicycle_management.availability.command.domain.BicycleNumber;
import pl.lodz.p.bicycle_management.availability.command.domain.UserId;

public interface BicycleMappingHelper {

    default String map(BicycleNumber value) {
        return value != null ? value.asString() : null;
    }

    default Integer map(UserId value) {
        return value != null ? value.userId() : null;
    }
}
