package pl.lodz.p.bicycle_management.rental.application;

import pl.lodz.p.bicycle_management.rental.domain.*;

import java.math.BigDecimal;
import java.sql.Time;

public class AdminReturnPolicy implements ReturnPolicy {

    @Override
    public Money finalizeRent(RentService rentService, UserId loggedInUser, RentNumber rentNumber) {
        Rent rent = rentService.findByRentNumber(rentNumber);
        rentService.delete(new RentId(rent.getId()));
        return new Money(new BigDecimal(0));
    }
}
