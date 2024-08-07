package pl.lodz.p.bicycle_management.payment.command.domain;

public class RegularRentPaymentPolicy implements RentPaymentPolicy {

    @Override
    public Money calculatePriceForRent(Integer timeInMinutes) {
        return Money.of(timeInMinutes * 1.1); // simple for now
    }
}
