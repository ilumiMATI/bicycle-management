package pl.lodz.p.bicycle_management.rental.application;

import lombok.extern.java.Log;
import pl.lodz.p.bicycle_management.rental.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Log
public class UserReturnPolicy implements ReturnPolicy {
    private final Money moneyPerHour = new Money(new BigDecimal("20"));
    private final long freeTimeInMinutes = 5;

    @Override
    public Money finalizeRent(RentService rentService, UserId loggedInUserId, RentNumber rentNumber) {
        var now = LocalDateTime.now();

        Rent rent = rentService.findByRentNumber(rentNumber);
        if (!rent.getUserId().equals(loggedInUserId)) {
            throw new MethodNotAllowedException();
        }

        rentService.delete(new RentId(rent.getId()));
        // time is measured in seconds just to speed up manual testing
        long minutes = rent.getTimeRented().until(now, ChronoUnit.SECONDS);
        if (minutes < freeTimeInMinutes) {
            return new Money(new BigDecimal(0));
        } else {
            minutes -= freeTimeInMinutes;
            Double hours = minutes / 60.0;
            log.info(minutes + " minutes needs to be paid");
            return moneyPerHour.multiply(new Money(new BigDecimal(hours)));
        }
    }
}
