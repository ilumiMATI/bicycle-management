package pl.lodz.p.bicycle_management.payment.command.application;

import java.math.BigDecimal;

public record WalletPayCommand(
        Integer userId,
        BigDecimal amount
) {
}
