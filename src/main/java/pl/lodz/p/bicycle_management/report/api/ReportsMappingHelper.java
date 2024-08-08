package pl.lodz.p.bicycle_management.report.api;

import pl.lodz.p.bicycle_management.report.domain.BicycleNumber;
import pl.lodz.p.bicycle_management.report.domain.Money;
import pl.lodz.p.bicycle_management.report.domain.RentalNumber;
import pl.lodz.p.bicycle_management.report.domain.UserId;

import java.math.BigDecimal;

public interface ReportsMappingHelper {

    default String mapBicycleNumber(BicycleNumber value) {
        return value != null ? value.asString() : null;
    }
    default BicycleNumber mapBicycleNumber(String value) {
        return value != null ? BicycleNumber.of(value) : null;
    }

    default Integer mapUserId(UserId value) {
        return value != null ? value.userId() : null;
    }
    default UserId mapUserId(Integer value) {
        return value != null ? UserId.of(value) : null;
    }

    default String mapRentalNumber(RentalNumber value) {
        return value != null ? value.asString() : null;
    }
    default RentalNumber mapRentalNumber(String value) {
        return value != null ? RentalNumber.of(value) : null;
    }

    default BigDecimal mapMoney(Money value) {
        return value != null ? value.amount() : null;
    }
    default Money mapMoney(BigDecimal value) {
        return value != null ? Money.of(value) : null;
    }
}
