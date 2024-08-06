package pl.lodz.p.bicycle_management.payment.query.facade;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.lodz.p.bicycle_management.payment.command.domain.UserWallet;

@Mapper(componentModel = "spring")
public interface UserWalletDtoMapper {
    @Mapping(target = "userId", source = "userId.userId")
    @Mapping(target = "money", source = "money.amount")
    UserWalletDto toDto(UserWallet userWallet);

    @Mapping(target = "userId.userId", source = "userId")
    @Mapping(target = "money.amount", source = "money")
    UserWallet toDomain(UserWalletDto userWalletDto);
}
