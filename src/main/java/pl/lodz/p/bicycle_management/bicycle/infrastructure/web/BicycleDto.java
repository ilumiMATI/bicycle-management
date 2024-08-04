package pl.lodz.p.bicycle_management.bicycle.infrastructure.web;

public record BicycleDto(
        Integer id,
        String bicycleId,
        String model,
        String brand,
        Integer batteryChargeDesign,
        Integer batteryChargeCurrent,
        Integer version
) {
}
