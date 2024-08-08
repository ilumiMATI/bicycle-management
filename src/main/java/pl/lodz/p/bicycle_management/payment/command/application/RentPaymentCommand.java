package pl.lodz.p.bicycle_management.payment.command.application;

public record RentPaymentCommand (
        String rentalNumber,
        Integer userId,
        Integer timeInMinutes
) {}
