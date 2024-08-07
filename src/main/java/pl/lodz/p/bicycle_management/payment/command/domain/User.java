package pl.lodz.p.bicycle_management.payment.command.domain;

public record User(
        Integer id,
        UserRole role
) {}