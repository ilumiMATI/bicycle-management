package pl.lodz.p.bicycle_management.payment.query.facade;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWallet;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageUserWalletDtoMapper {
    @Mapping(target = "wallets", qualifiedByName = "toListDto")
    PageUserWalletDto toPageDto(PageUserWallet pageUserWallet);

    @Named("toListDto")
    @IterableMapping(qualifiedByName = "toDto")
    List<UserWalletDto> toListDto(List<UserWallet> pageUserWalletList);

    @Named("toDto")
    @Mapping(target = "userId", source = "userId.userId")
    @Mapping(target = "money", source = "money.amount")
    UserWalletDto toDto(UserWallet domain);
}
