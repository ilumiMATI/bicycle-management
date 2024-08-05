package pl.lodz.p.bicycle_management.payment.command.infrastructure.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWallet;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWalletAlreadyExistsException;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWalletRepository;

import java.util.Optional;

@Log
@Repository
@RequiredArgsConstructor
public class UserWalletStorageAdapter implements UserWalletRepository {
    private final JpaUserWalletRepository jpaUserWalletRepository;

    @Override
    public UserWallet save(final UserWallet userWallet) {
        try {
            UserWallet saved = jpaUserWalletRepository.save(userWallet);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("User Rentals for User " + userWallet.getUserId() + " already exits in db");
            throw new UserWalletAlreadyExistsException();
        }
    }

    @Override
    public Optional<UserWallet> findByUserId(final UserId userId) {
        return jpaUserWalletRepository.findByUserId(userId);
    }

    @Override
    public void removeByUserId(final UserId userId) {
        jpaUserWalletRepository.deleteByUserId(userId);
    }
}
