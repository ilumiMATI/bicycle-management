package pl.lodz.p.bicycle_management.payment.command.domain;

import java.util.Optional;

public interface UserWalletRepository {
    UserWallet save(UserWallet userWallet);

    Optional<UserWallet> findByUserId(UserId userId);

    void removeByUserId(UserId userId);
}
