package pl.lodz.p.bicycle_management.payment.command.infrastructure.storage;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWallet;

import java.util.Optional;

public interface JpaUserWalletRepository extends JpaRepository<UserWallet, Integer> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<UserWallet> findByUserId(UserId userId);
    void deleteByUserId(UserId userId);
}
