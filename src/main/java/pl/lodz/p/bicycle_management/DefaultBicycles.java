package pl.lodz.p.bicycle_management;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.web.BicycleDto;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.web.BicycleDtoMapper;
import pl.lodz.p.bicycle_management.bicycle.application.BicycleService;

import java.util.List;

@RequiredArgsConstructor
@Component
@Log
public class DefaultBicycles implements CommandLineRunner {
    final private BicycleDtoMapper bicycleDtoMapper;
    final private BicycleService bicycleService;

    private final List<BicycleDto> bicycles = List.of(
            new BicycleDto(
                    null,
                    "bike1",
                    "TourRay E 3.0 Gent T350",
                    "R-RAYMON",
                    468,
                    435,
                    null
            ),
            new BicycleDto(
                    null,
                    "bike2",
                    "SX Youth Blue 14\"",
                    "Ecobike",
                    468,
                    256,
                    null
            ),
            new BicycleDto(
                    null,
                    "bike3",
                    "e-Orkan M 1.0",
                    "ROMET",
                    500,
                    450,
                    null
            )
    );

    @Override
    public void run(String... args) throws Exception {
        try {
            for (BicycleDto bicycleDto : bicycles ) {
                bicycleService.addBicycle(bicycleDtoMapper.toDomain(bicycleDto));
            }
        } catch (Exception ex) {
            log.warning(ex.getMessage());
        }
    }
}
