package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.lodz.p.bicycle_management.rental.domain.*;

@Mapper(componentModel = "spring")
public interface RentDtoMapper {

    //@Mapping(target = "id", qualifiedBy = RentIdMapping.ToRentId.class)
    @Mapping(target = "rentNumber", qualifiedBy = RentNumberMapping.ToRentNumberId.class)
    @Mapping(target = "userId", qualifiedBy = UserIdMapping.ToUserId.class)
    @Mapping(target = "bicycleId", qualifiedBy = BicycleIdMapping.ToBicycleId.class)
    Rent toDomain(RentDto rentDto);

    //@Mapping(target = "id", qualifiedBy = RentIdMapping.FromRentId.class)
    @Mapping(target = "rentNumber", qualifiedBy = RentNumberMapping.FromRentNumberId.class)
    @Mapping(target = "userId", qualifiedBy = UserIdMapping.FromUserId.class)
    @Mapping(target = "bicycleId", qualifiedBy = BicycleIdMapping.FromBicycleId.class)
    RentDto toDto(Rent rent);

    @RentIdMapping.ToRentId
    static RentId toRentId(Integer id) {
        return new RentId(id);
    }
    @RentIdMapping.FromRentId
    static Integer fromRentId(RentId rentId) {
        return rentId.getId();
    }

    @UserIdMapping.ToUserId
    static UserId toUserId(Integer id) {
        return new UserId(id);
    }
    @UserIdMapping.FromUserId
    static Integer fromUserId(UserId userId) {
        return userId.id();
    }

    @BicycleIdMapping.ToBicycleId
    static BicycleId fromBicycleId(Integer id) {
        return new BicycleId(id);
    }
    @BicycleIdMapping.FromBicycleId
    static Integer toBicycleId(BicycleId bicycleId) {
        return bicycleId.id();
    }

    @RentNumberMapping.ToRentNumberId
    static RentNumber fromRentNumber(String rentNumber) {
        return new RentNumber(rentNumber);
    }
    @RentNumberMapping.FromRentNumberId
    static String fromRentNumber(RentNumber rentNumber) {
        return rentNumber.getValue();
    }
}
