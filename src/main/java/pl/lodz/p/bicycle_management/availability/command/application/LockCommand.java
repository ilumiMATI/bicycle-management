package pl.lodz.p.bicycle_management.availability.command.application;

public record LockCommand(
        String bicycleId,
        Integer userId
) {}
