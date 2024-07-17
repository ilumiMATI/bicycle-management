package pl.lodz.p.bicycle_management.bicycle.application.type;

public record BicycleTypeDto (
        Integer id,
        String brand,
        String model,
        Integer productionYear
) {
}
