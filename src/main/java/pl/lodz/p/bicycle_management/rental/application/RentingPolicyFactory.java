package pl.lodz.p.bicycle_management.rental.application;

import lombok.extern.java.Log;
import pl.lodz.p.bicycle_management.rental.domain.MethodNotAllowedException;
import pl.lodz.p.bicycle_management.rental.domain.User;
import pl.lodz.p.bicycle_management.rental.domain.UserId;
import pl.lodz.p.bicycle_management.rental.domain.UserRole;


public class RentingPolicyFactory {
    public static RentingPolicy prepareRentingPolicy(User loggedInUser, UserId rentingUserId) {
        //if (rentingUserId == null) { rentingUserId = loggedInUser.getId(); }

        if(loggedInUser.getRole() == UserRole.ADMIN) {
            return new AdminRentingPolicy(loggedInUser.getId(), rentingUserId);
        } else if (loggedInUser.getRole() == UserRole.USER) {
            return new UserRentingPolicy(loggedInUser.getId(), rentingUserId);
        } else throw new MethodNotAllowedException();
    }
}
