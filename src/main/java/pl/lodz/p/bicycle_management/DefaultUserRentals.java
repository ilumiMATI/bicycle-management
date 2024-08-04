package pl.lodz.p.bicycle_management;

import pl.lodz.p.bicycle_management.rental.command.application.CreateCommand;
import pl.lodz.p.bicycle_management.rental.command.application.RentalService;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log
public class DefaultUserRentals implements CommandLineRunner {

    private final RentalService rentalService;

    public DefaultUserRentals(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    @Override
    public void run(String... args) {
        try {
            rentalService.create(new CreateCommand(1));
            rentalService.create(new CreateCommand(2));
        } catch (Exception ex) {
            log.warning(ex.getMessage());
        }
    }

}
