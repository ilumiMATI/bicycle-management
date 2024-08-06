package pl.lodz.p.bicycle_management.payment.query.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWallet;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWalletNotFoundException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public class UserWalletFacade {
    private final JpaQueryUserWalletRepository jpaQueryUserWalletRepository;
    private final UserWalletDtoMapper userWalletDtoMapper;

    public UserWalletDto findByUserId(Integer userId) {
        final Optional<UserWallet> userWallet = jpaQueryUserWalletRepository.findUserWalletByUserId(UserId.of(userId));
        return userWalletDtoMapper.toDto(userWallet.orElseThrow(UserWalletNotFoundException::new));
    }
    // TODO: Add Page Wallet
}
