package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.rental.domain.PageRent;
import pl.lodz.p.bicycle_management.rental.domain.Rent;

import java.util.List;

@Mapper(componentModel = "spring", uses = RentDtoMapper.class)
public interface PageRentDtoMapper {
    @Mapping(target = "rents", qualifiedByName = "toPageRentDtoList")
    PageRentDto toPageDto(PageRent pageRent);

    @Named("toPageRentDtoList")
    @IterableMapping(qualifiedByName = "rentToRentDto")
    List<RentDto> toListDto(List<Rent> rents);

    @Named("rentToRentDto")
    //@Mapping(target = "id", qualifiedBy = RentIdMapping.FromRentId.class)
    @Mapping(target = "rentNumber", qualifiedBy = RentNumberMapping.FromRentNumberId.class)
    @Mapping(target = "userId", qualifiedBy = UserIdMapping.FromUserId.class)
    @Mapping(target = "bicycleId", qualifiedBy = BicycleIdMapping.FromBicycleId.class)
    RentDto toDto(Rent rent);

    @Named("rentDtoToRent")
    //@Mapping(target = "id", qualifiedBy = RentIdMapping.ToRentId.class)
    @Mapping(target = "rentNumber", qualifiedBy = RentNumberMapping.ToRentNumberId.class)
    @Mapping(target = "userId", qualifiedBy = UserIdMapping.ToUserId.class)
    @Mapping(target = "bicycleId", qualifiedBy = BicycleIdMapping.ToBicycleId.class)
    Rent toDomain(RentDto rentDto);
}
