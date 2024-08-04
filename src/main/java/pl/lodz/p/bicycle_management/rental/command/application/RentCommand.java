package pl.lodz.p.bicycle_management.rental.command.application;

public record RentCommand(
        String bicycleId,
        Integer userId
) {}
