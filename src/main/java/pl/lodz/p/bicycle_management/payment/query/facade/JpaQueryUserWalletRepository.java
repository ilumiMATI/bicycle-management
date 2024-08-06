package pl.lodz.p.bicycle_management.payment.query.facade;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWallet;

import java.util.Optional;

public interface JpaQueryUserWalletRepository extends JpaRepository<UserWallet, Integer> {
    Optional<UserWallet> findUserWalletByUserId(UserId userId);

}
