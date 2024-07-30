package pl.lodz.p.bicycle_management.rental.infrastructure.web;

import pl.lodz.p.bicycle_management.bicycle.infrastructure.web.BicycleDto;
import pl.lodz.p.bicycle_management.user.infrastructure.web.UserDto;

import java.time.LocalDateTime;

public record RentDto(
        Integer id,
        UserDto user,
        BicycleDto bicycle,
        LocalDateTime timeRented
) {
}
