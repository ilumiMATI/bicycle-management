package pl.lodz.p.bicycle_management.rental.query.facade;

import java.util.List;

public record PageUserRentalsDto(
        List<UserRentalsDto> rentals,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
