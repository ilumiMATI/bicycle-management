package pl.lodz.p.bicycle_management.bicycle.application;

import pl.lodz.p.bicycle_management.bicycle.application.type.BicycleTypeDto;

public record BicycleDto(
        Integer id,
        BicycleTypeDto bicycleType,
        Integer batteryChargeDesign,
        Integer batteryChargeCurrent
) {
}
