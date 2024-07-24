package pl.lodz.p.bicycle_management.rental.application;

import pl.lodz.p.bicycle_management.bicycle.application.BicycleDto;
import pl.lodz.p.bicycle_management.user.application.UserDto;

import java.time.LocalDateTime;

public record RentDto(
        Integer id,
        UserDto user,
        BicycleDto bicycle,
        LocalDateTime timeRented
) {
}
