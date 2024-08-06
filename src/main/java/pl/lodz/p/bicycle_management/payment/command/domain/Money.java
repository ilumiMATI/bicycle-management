package pl.lodz.p.bicycle_management.payment.command.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(
        BigDecimal amount
) {

    public static Money of(BigDecimal amount) {
        return new Money(amount.setScale(2, RoundingMode.HALF_UP));
    }

    public static Money of(Double amount) {
        return new Money(BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP));
    }

    public static Money of(Integer amount) {
        return new Money(BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP));
    }

    public Money add(Money money) {
        return new Money(amount.add(money.amount()));
    }

    public Money subtract(Money money) {
        if (amount.compareTo(money.amount) < 0) {
            throw new PaymentInsufficientFundsException();
        }
        return new Money(amount.subtract(money.amount()));
    }

    public Money multiply(Money money) {
        return new Money(amount.multiply(money.amount()));
    }

    public Money divide(Money money) {
        return new Money(amount.divide(money.amount(), 2, RoundingMode.HALF_UP));
    }

    public String asString() {
        return amount.toString();
    }

    public BigDecimal value() {
        return amount;
    }
}
