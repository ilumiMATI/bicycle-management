package pl.lodz.p.bicycle_management.payment.command.domain;

public interface RentPaymentPolicy {
    Money calculatePriceForRent(Integer timeInMinutes); // There could be also different bike types for different price
}
