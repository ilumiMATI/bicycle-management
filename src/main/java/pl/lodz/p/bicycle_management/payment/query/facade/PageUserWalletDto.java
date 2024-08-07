package pl.lodz.p.bicycle_management.payment.query.facade;

import java.util.List;

public record PageUserWalletDto(
        List<UserWalletDto> wallets,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
