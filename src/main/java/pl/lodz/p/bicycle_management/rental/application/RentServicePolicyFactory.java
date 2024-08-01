package pl.lodz.p.bicycle_management.rental.application;

import pl.lodz.p.bicycle_management.rental.domain.MethodNotAllowedException;
import pl.lodz.p.bicycle_management.rental.domain.User;
import pl.lodz.p.bicycle_management.rental.domain.UserId;
import pl.lodz.p.bicycle_management.rental.domain.UserRole;


public class RentServicePolicyFactory {
    public static RentingPolicy prepareRentingPolicy(User loggedInUser) {
        if(loggedInUser.getRole() == UserRole.ADMIN) {
            return new AdminRentingPolicy();
        } else if (loggedInUser.getRole() == UserRole.USER) {
            return new UserRentingPolicy();
        } else throw new MethodNotAllowedException();
    }

    public static ReturnPolicy prepareReturnPolicy(User loggedInUser) {
        if(loggedInUser.getRole() == UserRole.ADMIN) {
            return new AdminReturnPolicy();
        } else if (loggedInUser.getRole() == UserRole.USER) {
            return new UserReturnPolicy();
        } else throw new MethodNotAllowedException();
    }
}
