package pl.lodz.p.bicycle_management.payment.command.domain;

import org.springframework.data.domain.Pageable;
import pl.lodz.p.bicycle_management.payment.query.facade.PageUserWallet;

import java.util.Optional;

public interface UserWalletRepository {
    UserWallet save(UserWallet userWallet);

    Optional<UserWallet> findByUserId(UserId userId);

    PageUserWallet findAll(Pageable pageable);

    void removeByUserId(UserId userId);
}
