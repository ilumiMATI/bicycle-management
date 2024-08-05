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
        return new Money(money.amount.add(money.amount()));
    }

    public Money subtract(Money money) {
        return new Money(money.amount.subtract(money.amount()));
    }

    public Money multiply(Money money) {
        return new Money(money.amount.multiply(money.amount()));
    }

    public Money divide(Money money) {
        return new Money(money.amount.divide(money.amount(), 2, RoundingMode.HALF_UP));
    }

    public String asString() {
        return amount.toString();
    }
}
