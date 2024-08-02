package pl.lodz.p.bicycle_management.availability.command.domain;

public record UserId(Integer value) {

    public static UserId of(Integer value) {
        return new UserId(value);
    }

    public String asString() {
        return value.toString();
    }
}
