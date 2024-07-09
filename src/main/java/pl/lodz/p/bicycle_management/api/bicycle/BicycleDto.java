package pl.lodz.p.bicycle_management.api.bicycle;

public record BicycleDto (
        Integer id,
        String brand,
        String model,
        Integer productionYear,
        Integer maxSpeed,
        Integer batteryChargeDesign,
        Integer batteryChargeCurrent
) {
}
