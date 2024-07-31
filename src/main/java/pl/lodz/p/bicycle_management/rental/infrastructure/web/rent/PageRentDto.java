package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import java.util.List;

public record PageRentDto(
        List<RentDto> rents,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
