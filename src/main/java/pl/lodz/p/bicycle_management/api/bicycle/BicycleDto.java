package pl.lodz.p.bicycle_management.api.bicycle;

import java.util.UUID;

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
