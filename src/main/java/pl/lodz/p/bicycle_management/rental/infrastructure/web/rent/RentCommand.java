package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import java.util.List;

public record RentCommand(
        Integer userId,
        List<Integer> bicycleIds
) {
}
