package pl.lodz.p.bicycle_management.rental.application;

import pl.lodz.p.bicycle_management.rental.domain.BicycleId;
import pl.lodz.p.bicycle_management.rental.domain.Rent;
import pl.lodz.p.bicycle_management.rental.domain.UserId;

import java.util.List;

public interface RentingPolicy {
    public List<Rent> createRents(RentService rentService, UserId loggedInUserId, UserId rentingUserId, List<BicycleId> bicycleIds);
}
