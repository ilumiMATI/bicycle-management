package pl.lodz.p.bicycle_management.rental.query.facade;


import pl.lodz.p.bicycle_management.rental.command.domain.BicycleNumber;

public interface BicycleNumberMappingHelper {
    default String map(BicycleNumber value) {
        return value != null ? value.asString() : null;
    }
}
