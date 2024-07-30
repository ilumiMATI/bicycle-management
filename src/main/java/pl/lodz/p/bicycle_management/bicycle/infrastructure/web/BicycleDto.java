package pl.lodz.p.bicycle_management.bicycle.infrastructure.web;

public record BicycleDto(
        Integer id,
        BicycleTypeDto bicycleType,
        Integer batteryChargeDesign,
        Integer batteryChargeCurrent
) {
}
