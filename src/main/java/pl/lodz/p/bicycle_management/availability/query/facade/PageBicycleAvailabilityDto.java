package pl.lodz.p.bicycle_management.availability.query.facade;

import java.util.List;

public record PageBicycleAvailabilityDto(
        List<BicycleAvailabilityDto> availabilities,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
