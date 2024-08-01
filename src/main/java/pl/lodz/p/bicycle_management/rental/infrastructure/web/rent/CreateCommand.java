package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import java.util.List;

public record CreateCommand(
        Integer userId,
        List<Integer> bicycleIds
) {
}
