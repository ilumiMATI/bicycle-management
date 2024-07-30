package pl.lodz.p.bicycle_management.bicycle.infrastructure.web;

public record BicycleMinimalDto(
        Integer id,
        Integer bicycleTypeId,
        Integer batteryChargeDesign,
        Integer batteryChargeCurrent
) {
}
