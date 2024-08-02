package pl.lodz.p.bicycle_management.availability.command.domain;

public record User(
        Integer id,
        UserRole role
) {}