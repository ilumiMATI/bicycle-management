package pl.lodz.p.bicycle_management.rental.command.application;

public record RentCommand(
        String bicycleNumber,
        Integer userId
) {}
