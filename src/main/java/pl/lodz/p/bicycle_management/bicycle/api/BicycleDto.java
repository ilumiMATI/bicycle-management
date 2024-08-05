package pl.lodz.p.bicycle_management.bicycle.api;

public record BicycleDto(
        Integer id,
        String bicycleNumber,
        String model,
        String brand,
        Integer batteryChargeDesign,
        Integer batteryChargeCurrent,
        Integer version
) {
}
