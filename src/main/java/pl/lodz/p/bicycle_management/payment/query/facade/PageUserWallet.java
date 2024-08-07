package pl.lodz.p.bicycle_management.payment.query.facade;

import lombok.Value;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWallet;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Value
public class PageUserWallet implements Serializable {
    List<UserWallet> wallets;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
