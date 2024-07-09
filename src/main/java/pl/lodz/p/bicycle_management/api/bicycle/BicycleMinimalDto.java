package pl.lodz.p.bicycle_management.api.bicycle;

public record BicycleMinimalDto(
        Integer id,
        Integer bicycleTypeId,
        Integer batteryChargeDesign,
        Integer batteryChargeCurrent
) {
}
