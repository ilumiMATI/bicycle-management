package pl.lodz.p.bicycle_management.rental.infrastructure.web;

import java.time.LocalDateTime;

public record RentMinimalDto(
        Integer id,
        Integer userId,
        Integer bicycleId,
        LocalDateTime timeRented
) {
}
