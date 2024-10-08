package pl.lodz.p.bicycle_management.rental.command.domain;

public record UserId(Integer userId) {
    public static UserId of(Integer userId) {
        return new UserId(userId);
    }

    public String asString() {
        return userId.toString();
    }

    public Integer value() {
        return userId;
    }
}
