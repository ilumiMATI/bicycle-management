package pl.lodz.p.bicycle_management.rental.application;

import lombok.AllArgsConstructor;
import org.mapstruct.control.MappingControl;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AdminRentingPolicy implements RentingPolicy {

    @Override
    public List<Rent> createRents(RentService rentService, UserId loggedInUserId, UserId rentingUserId, List<BicycleId> bicycleIds) {
        if (rentingUserId == null) {rentingUserId = loggedInUserId;}
        List<Rent> rents = new ArrayList<Rent>();
        for (BicycleId bicycleId : bicycleIds) {
            rents.add(new Rent(rentingUserId, bicycleId));
        }
        return rents;
    }
}
