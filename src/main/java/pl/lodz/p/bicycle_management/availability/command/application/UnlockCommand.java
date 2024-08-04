package pl.lodz.p.bicycle_management.availability.command.application;

public record UnlockCommand(
        String bicycleNumber,
        Integer userId
) {}
