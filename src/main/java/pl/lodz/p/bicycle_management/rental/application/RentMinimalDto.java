package pl.lodz.p.bicycle_management.rental.application;

import java.time.LocalDateTime;

public record RentMinimalDto(
        Integer id,
        Integer userId,
        Integer bicycleId,
        LocalDateTime timeRented
) {
}
