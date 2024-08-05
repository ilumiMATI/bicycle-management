package pl.lodz.p.bicycle_management.bicycle.api;

import java.util.List;

public record PageBicycleDto(
        List<BicycleDto> bicycles,
        Integer currentPage,
        Integer totalPages,
        Long totalElements
) {
}
