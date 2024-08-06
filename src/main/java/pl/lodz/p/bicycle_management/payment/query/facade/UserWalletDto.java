package pl.lodz.p.bicycle_management.payment.query.facade;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Version;

import java.math.BigDecimal;

public record UserWalletDto(
        Integer id,
        Integer userId,
        BigDecimal money,
        Integer version
) {
}
