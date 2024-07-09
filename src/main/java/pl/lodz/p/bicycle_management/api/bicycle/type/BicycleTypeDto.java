package pl.lodz.p.bicycle_management.api.bicycle.type;

public record BicycleTypeDto (
        Integer id,
        String brand,
        String model,
        Integer productionYear
) {
}
