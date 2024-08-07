package pl.lodz.p.bicycle_management.payment.command.application;

public record RentPaymentCommand (
    Integer userId,
    Integer timeInMinutes
) {}
