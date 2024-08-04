package pl.lodz.p.bicycle_management.availability.command.application;

public record UnlockCommand(
        String bicycleId,
        Integer userId
) {}
