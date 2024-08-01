package pl.lodz.p.bicycle_management.rental.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
public class Money {
    BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public Money add(Money money) {
        return new Money(amount.add(money.amount));
    }
    public Money subtract(Money money) {
        return new Money(amount.subtract(money.amount));
    }
    public Money multiply(Money money) {
        return new Money(amount.multiply(money.amount));
    }
}
