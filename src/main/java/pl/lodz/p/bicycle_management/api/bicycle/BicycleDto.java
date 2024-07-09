package pl.lodz.p.bicycle_management.api.bicycle;

import pl.lodz.p.bicycle_management.api.bicycle.type.BicycleTypeDto;

public record BicycleDto(
        Integer id,
        BicycleTypeDto bicycleType,
        Integer batteryChargeDesign,
        Integer batteryChargeCurrent
) {
}
