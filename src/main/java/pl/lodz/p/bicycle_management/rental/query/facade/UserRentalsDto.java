package pl.lodz.p.bicycle_management.rental.query.facade;

import java.util.List;

public record UserRentalsDto(
        Integer userId,
        List<String> bicycles,
        Integer version
) {
}
