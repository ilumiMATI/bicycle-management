package pl.lodz.p.bicycle_management.rental.application;

import lombok.AllArgsConstructor;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class UserRentingPolicy implements RentingPolicy{
    private UserId loggedInUserId;
    private UserId rentingUserId;

    @Override
    public List<Rent> createRents(List<BicycleId> bicycleIds) {
        if ((bicycleIds.size() != 1) || (!loggedInUserId.equals(rentingUserId))) {
            throw new MethodNotAllowedException();
        }
        List<Rent> rents = new ArrayList<Rent>();
        rents.add(new Rent(rentingUserId, bicycleIds.get(0)));
        return rents;
    }
}
