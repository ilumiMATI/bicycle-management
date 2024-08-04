package pl.lodz.p.bicycle_management.rental.command.domain;

public record User(
        Integer id,
        UserRole role
) {}