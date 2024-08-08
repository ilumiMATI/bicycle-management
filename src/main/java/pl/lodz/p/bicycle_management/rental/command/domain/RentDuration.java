package pl.lodz.p.bicycle_management.rental.command.domain;

import java.time.LocalDateTime;

public record RentDuration(
        LocalDateTime startTime,
        Integer inMinutes
) {
    public static RentDuration of(LocalDateTime startTime, Integer inMinutes) {
        return new RentDuration(startTime, inMinutes);
    }

    public LocalDateTime endTime() {
        return startTime.plusMinutes(inMinutes);
    }
}
