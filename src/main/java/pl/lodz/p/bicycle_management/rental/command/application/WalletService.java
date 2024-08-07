package pl.lodz.p.bicycle_management.rental.command.application;

import pl.lodz.p.bicycle_management.rental.command.domain.UserId;

import java.math.BigDecimal;

public interface WalletService {
    boolean hasMoney(UserId userId, BigDecimal amount);
}
