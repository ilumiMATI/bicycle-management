package pl.lodz.p.bicycle_management.rental.application;

import pl.lodz.p.bicycle_management.rental.domain.Money;
import pl.lodz.p.bicycle_management.rental.domain.RentNumber;
import pl.lodz.p.bicycle_management.rental.domain.User;
import pl.lodz.p.bicycle_management.rental.domain.UserId;

import java.math.BigDecimal;
import java.sql.Time;

public interface ReturnPolicy {
    Money finalizeRent(RentService rentService, UserId loggedInUser, RentNumber rentNumber);
}
