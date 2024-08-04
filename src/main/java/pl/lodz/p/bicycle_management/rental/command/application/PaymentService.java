package pl.lodz.p.bicycle_management.rental.command.application;

public interface PaymentService {
    void payForRent(Integer userId, Integer timeInMinutes);
}
