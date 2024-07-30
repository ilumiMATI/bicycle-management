package pl.lodz.p.bicycle_management.rental.infrastructure.web;

import pl.lodz.p.bicycle_management.bicycle.infrastructure.web.BicycleDto;

import java.time.LocalDateTime;

public record RentDto(
        Integer id,
        String rentNumber,
        Integer userId,
        Integer bicycleId,
        LocalDateTime timeRented
) {
}