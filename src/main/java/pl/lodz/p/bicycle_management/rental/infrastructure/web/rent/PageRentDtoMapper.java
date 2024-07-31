package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.lodz.p.bicycle_management.rental.domain.PageRent;
import pl.lodz.p.bicycle_management.rental.domain.Rent;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageRentDtoMapper {
    @Mapping(target = "rents", qualifiedByName = "toPageRentDtoList")
    PageRentDto toPageDto(PageRent pageRent);

    @Named("toPageRentDtoList")
    @IterableMapping(qualifiedByName = "rentToRentDto")
    List<RentDto> toListDto(List<Rent> rents);

    @Named("rentToRentDto")
    RentDto toDto(Rent rent);

    @Named("rentDtoToRent")
    Rent toDomain(RentDto rentDto);
}
