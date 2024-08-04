package pl.lodz.p.bicycle_management.availability.command.application;

public record LockCommand(
        String bicycleNumber,
        Integer userId
) {}
