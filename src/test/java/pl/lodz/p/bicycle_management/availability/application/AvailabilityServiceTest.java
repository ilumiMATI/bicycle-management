package pl.lodz.p.bicycle_management.availability.application;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import pl.lodz.p.bicycle_management.BaseIT;
import pl.lodz.p.bicycle_management.TestBicycleFactory;
import pl.lodz.p.bicycle_management.TestClockConfiguration;
import pl.lodz.p.bicycle_management.availability.command.application.LockCommand;
import pl.lodz.p.bicycle_management.availability.command.application.UnlockCommand;
import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAlreadyLockedException;
import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAvailability;
import pl.lodz.p.bicycle_management.availability.command.domain.BicycleNumber;
import pl.lodz.p.bicycle_management.availability.command.domain.LockDuration;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class AvailabilityServiceTest extends BaseIT {

    @Test
    void fake_clock_should_allow_time_change_in_availability_service() {
        Bicycle bicycle = TestBicycleFactory.createBicycle();
        bicycleService.save(bicycle);

        availabilityService.lockBicycle(new LockCommand(bicycle.getBicycleNumber().asString(),1));
        clock.addMinutes(500);

        LockDuration lockDuration = availabilityService.unlockBicycle(new UnlockCommand(bicycle.getBicycleNumber().asString(),1));
        assertEquals(500, lockDuration.inMinutes());
    }

    @Test
    void fake_clock_should_allow_to_change_time() {
        final LocalDateTime start = LocalDateTime.now();
        clock.setDateTime(start);

        Integer diff = (int) start.until(clock.getCurrentDateTime(), ChronoUnit.MINUTES);
        assertEquals(0,diff);

        clock.addMinutes(500);
        assertNotEquals(start,clock.getCurrentDateTime());

        diff = (int) ChronoUnit.MINUTES.between(start, clock.getCurrentDateTime());

        assertEquals(500,diff);
        assertNotEquals(start,clock.getCurrentDateTime());
    }

    @Test
    void bicycle_availability_should_be_created_when_bicycle_is_created() {
        Bicycle bicycle = TestBicycleFactory.createBicycle();
        bicycleService.save(bicycle);

        try {
            BicycleAvailability bicycleAvailability = availabilityService
                    .findByBicycleNumber(BicycleNumber.of(bicycle.getBicycleNumber().asString()));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void bicycle_availability_should_be_deleted_when_bicycle_is_deleted() {
        Bicycle bicycle = TestBicycleFactory.createBicycle();
        bicycleService.save(bicycle);
        bicycleService.removeById(bicycle.getId());

        try {
            BicycleAvailability bicycleAvailability = availabilityService
                    .findByBicycleNumber(BicycleNumber.of(bicycle.getBicycleNumber().asString()));
            fail();
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    @Test
    void bicycle_cannot_be_locked_again() {
        Bicycle bicycle = TestBicycleFactory.createBicycle();
        bicycleService.save(bicycle);

        availabilityService.lockBicycle(new LockCommand(bicycle.getBicycleNumber().asString(),1));
        try {
            availabilityService.lockBicycle(new LockCommand(bicycle.getBicycleNumber().asString(), 1));
            fail();
        } catch (BicycleAlreadyLockedException ex) {
            assertTrue(true);
        }
    }

    @Test
    void bicycle_should_be_possible_to_unlock_even_when_it_is_already_unlocked() {
        Bicycle bicycle = TestBicycleFactory.createBicycle();
        bicycleService.save(bicycle);

        LockDuration lockDuration = availabilityService.unlockBicycle(new UnlockCommand(bicycle.getBicycleNumber().asString(),1));

        assertEquals(0,lockDuration.inMinutes());
        assertNull(lockDuration.startTime());
    }
}
