package pl.lodz.p.bicycle_management.user.domain;

import java.math.BigDecimal;

public interface PaymentService {
    void createWalletForUser(Integer id);
    void removeWalletForUser(Integer id);
}
