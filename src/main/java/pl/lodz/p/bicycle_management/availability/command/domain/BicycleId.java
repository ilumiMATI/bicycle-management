package pl.lodz.p.bicycle_management.availability.command.domain;

public record BicycleId(String value) {

    public static BicycleId of(String value) {
        return new BicycleId(value);
    }

    public String asString() {
        return value;
    }
}
