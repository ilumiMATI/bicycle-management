package pl.lodz.p.bicycle_management.rental.command.application;

public record ReturnCommand(
        String bicycleId,
        Integer userId
) {}
