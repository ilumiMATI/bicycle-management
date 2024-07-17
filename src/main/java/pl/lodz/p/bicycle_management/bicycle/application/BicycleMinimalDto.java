package pl.lodz.p.bicycle_management.bicycle.application;

public record BicycleMinimalDto(
        Integer id,
        Integer bicycleTypeId,
        Integer batteryChargeDesign,
        Integer batteryChargeCurrent
) {
}
