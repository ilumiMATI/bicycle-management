package pl.lodz.p.bicycle_management.payment.command.domain;

public record UserId(Integer id) {
    public static UserId of(Integer id
    ) {
        return new UserId(id);
    }

    public String asString() {
        return id.toString();
    }
}
