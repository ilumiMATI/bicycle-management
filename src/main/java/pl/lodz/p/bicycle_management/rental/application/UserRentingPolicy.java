package pl.lodz.p.bicycle_management.rental.application;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserRentingPolicy implements RentingPolicy{

    @Override
    public List<Rent> createRents(RentService rentService, UserId loggedInUserId, UserId rentingUserId,List<BicycleId> bicycleIds) {
        if ((bicycleIds.size() != 1) || (!loggedInUserId.equals(rentingUserId))) {
            throw new MethodNotAllowedException();
        }
        if (rentService.existsByUserId(rentingUserId)) {
            throw new RentAlreadyExistsException();
        }
        List<Rent> rents = new ArrayList<Rent>();
        rents.add(new Rent(rentingUserId, bicycleIds.get(0)));
        return rents;
    }
}
