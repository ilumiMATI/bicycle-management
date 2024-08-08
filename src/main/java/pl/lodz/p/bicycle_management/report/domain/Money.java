package pl.lodz.p.bicycle_management.report.domain;

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

    public int compareTo(Money money) {
        return amount.compareTo(money.amount());
    }

    public String asString() {
        return amount.toString();
    }

    public BigDecimal value() {
        return amount;
    }
}
