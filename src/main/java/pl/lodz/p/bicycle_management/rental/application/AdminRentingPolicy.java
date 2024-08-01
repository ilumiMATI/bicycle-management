package pl.lodz.p.bicycle_management.rental.application;

import lombok.AllArgsConstructor;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AdminRentingPolicy implements RentingPolicy {
    private UserId loggedInUserId;
    private UserId rentingUserId;

    @Override
    public List<Rent> createRents(List<BicycleId> bicycleIds) {
        List<Rent> rents = new ArrayList<Rent>();
        for (BicycleId bicycleId : bicycleIds) {
            rents.add(new Rent(rentingUserId, bicycleId));
        }
        return rents;
    }
}
