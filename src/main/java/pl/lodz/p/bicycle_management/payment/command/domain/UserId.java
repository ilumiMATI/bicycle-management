package pl.lodz.p.bicycle_management.payment.command.domain;

import java.io.Serializable;

public record UserId(Integer userId) {
    public static UserId of(Integer userId) {
        return new UserId(userId);
    }

    public String asString() {
        return userId.toString();
    }
}
