package pl.lodz.p.bicycle_management.payment.query.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWallet;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWalletNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public class UserWalletFacade {
    private final JpaQueryUserWalletRepository jpaQueryUserWalletRepository;
    private final UserWalletDtoMapper userWalletDtoMapper;
    private final PageUserWalletDtoMapper pageUserWalletDtoMapper;

    public UserWalletDto findByUserId(Integer userId) {
        final Optional<UserWallet> userWallet = jpaQueryUserWalletRepository.findUserWalletByUserId(UserId.of(userId));
        return userWalletDtoMapper.toDto(userWallet.orElseThrow(UserWalletNotFoundException::new));
    }

    public PageUserWalletDto findAll(final Pageable pageable) {
        Page<UserWallet> pageOfUserWalletEntity = jpaQueryUserWalletRepository.findAll(pageable);
        List<UserWallet> userWalletsOnCurrentPage = new ArrayList<>(pageOfUserWalletEntity.getContent());

        final PageUserWallet pageReservation = new PageUserWallet(
                userWalletsOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfUserWalletEntity.getTotalPages(),
                pageOfUserWalletEntity.getTotalElements()
        );
        return pageUserWalletDtoMapper.toPageDto(pageReservation);
    }
}
