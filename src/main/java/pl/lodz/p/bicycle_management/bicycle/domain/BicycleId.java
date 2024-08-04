package pl.lodz.p.bicycle_management.bicycle.domain;

public record BicycleId(String bicycleId) {
    public static BicycleId of(String bicycleId) {
        return new BicycleId(bicycleId);
    }
    public String asString() {
        return bicycleId;
    }
}
