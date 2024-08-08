package pl.lodz.p.bicycle_management.availability.command.domain;

import java.time.LocalDateTime;

public record LockDuration(
        LocalDateTime startTime,
        Integer inMinutes
) {
    public static LockDuration of(LocalDateTime startTime, Integer inMinutes) {
        return new LockDuration(startTime, inMinutes);
    }
}
