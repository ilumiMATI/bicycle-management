package pl.lodz.p.bicycle_management.api.bicycle;

public record BicycleDto (
        String Brand,
        String Model,
        Integer ProductionYear,
        Integer MaxSpeed,
        Integer BatteryChargeDesign,
        Integer BatteryChargeCurrent
) {
}
