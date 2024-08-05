package pl.lodz.p.bicycle_management;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.bicycle.api.BicycleDto;
import pl.lodz.p.bicycle_management.bicycle.api.BicycleDtoMapper;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNumber;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleService;

import java.util.List;

@RequiredArgsConstructor
@Component
@Log
public class DefaultBicycles implements CommandLineRunner {
    final private BicycleService bicycleService;

    private final List<Bicycle> bicycles = List.of(
            new Bicycle(
                    new BicycleNumber("bike1"),
                    "TourRay E 3.0 Gent T350",
                    "R-RAYMON",
                    468,
                    435
            ),
            new Bicycle(
                    new BicycleNumber("bike2"),
                    "SX Youth Blue 14\"",
                    "Ecobike",
                    468,
                    256
            ),
            new Bicycle(
                    new BicycleNumber("bike3"),
                    "e-Orkan M 1.0",
                    "ROMET",
                    500,
                    450
            )
    );

    @Override
    public void run(String... args) throws Exception {
        try {
            bicycles.forEach(bicycleService::save);
        } catch (Exception ex) {
            log.warning(ex.getMessage());
        }
    }
}
