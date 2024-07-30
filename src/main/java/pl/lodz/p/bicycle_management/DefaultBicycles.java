package pl.lodz.p.bicycle_management;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.web.BicycleDtoMapper;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.web.BicycleMinimalDto;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.web.BicycleTypeDto;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.web.BicycleTypeDtoMapper;
import pl.lodz.p.bicycle_management.bicycle.application.BicycleService;
import pl.lodz.p.bicycle_management.bicycle.application.BicycleTypeService;

@RequiredArgsConstructor
@Component
public class DefaultBicycles implements CommandLineRunner {
    final private BicycleDtoMapper bicycleDtoMapper;
    final private BicycleService bicycleService;
    final private BicycleTypeDtoMapper bicycleTypeDtoMapper;
    final private BicycleTypeService bicycleTypeService;

    private final BicycleTypeDto bicycleTypes[] = {
            new BicycleTypeDto(
                    null,
                    "Ecobike",
                    "SX Youth Blue 14\"",
                    2022
            ),
            new BicycleTypeDto(
                    null,
                    "R-RAYMON",
                    "TourRay E 3.0 Gent T350",
                    2023
            ),
            new BicycleTypeDto(
                    null,
                    "ROMET",
                    "e-Orkan M 1.0",
                    2024
            )

    };

    private final BicycleMinimalDto bicycles[] = {
            new BicycleMinimalDto(
                    null,
                    1,
                    468,
                    435
            ),
            new BicycleMinimalDto(
                    null,
                    1,
                    468,
                    256
            ),
            new BicycleMinimalDto(
                    null,
                    2,
                    500,
                    450
            ),
            new BicycleMinimalDto(
                    null,
                    3,
                    504,
                    489
            )
    };

    @Override
    public void run(String... args) throws Exception {
        for (BicycleTypeDto bicycleType : bicycleTypes) {
            bicycleTypeService.addBicycleType(bicycleTypeDtoMapper.toDomain(bicycleType));
        }
        for (BicycleMinimalDto bicycle : bicycles) {
            bicycleService.addBicycle(bicycleDtoMapper.toDomain(bicycle));
        }
    }
}
