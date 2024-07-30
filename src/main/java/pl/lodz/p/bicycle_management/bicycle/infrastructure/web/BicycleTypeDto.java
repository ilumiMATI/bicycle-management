package pl.lodz.p.bicycle_management.bicycle.infrastructure.web;

public record BicycleTypeDto (
        Integer id,
        String brand,
        String model,
        Integer productionYear
) {
}
