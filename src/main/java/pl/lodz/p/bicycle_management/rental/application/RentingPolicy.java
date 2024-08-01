package pl.lodz.p.bicycle_management.rental.application;

import lombok.AllArgsConstructor;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.rental.domain.BicycleId;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.rental.domain.UserId;

import java.util.List;

public interface RentingPolicy {
    public List<Rent> createRents(List<BicycleId> bicycleIds);
}
