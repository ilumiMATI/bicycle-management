package pl.lodz.p.bicycle_management.availability.command.domain;

public record UserId(Integer userId) {

    public static UserId of(Integer userId
    ) {
        return new UserId(userId);
    }

    public String asString() {
        return userId.toString();
    }
}
