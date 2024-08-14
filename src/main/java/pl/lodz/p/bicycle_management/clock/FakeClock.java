package pl.lodz.p.bicycle_management.clock;

import lombok.AllArgsConstructor;

import java.time.*;

@AllArgsConstructor
public class FakeClock implements Clock {
    private Long fixedTime; // Time in milliseconds

    @Override
    public LocalDateTime getCurrentDateTime() {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = Instant.ofEpochMilli(this.fixedTime);
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    public void setDateTime(LocalDateTime newDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();  // Get the system default time zone
        ZonedDateTime zonedDateTime = newDateTime.atZone(zoneId);  // Convert LocalDateTime to ZonedDateTime
        long millis = zonedDateTime.toInstant().toEpochMilli();  // Get milliseconds from Instant
        int nanos = zonedDateTime.getNano();  // Get nanoseconds from ZonedDateTime

        // Calculate the fractional milliseconds from nanoseconds
        long fractionalMillis = nanos / 1_000_000;
        // Set fixedTime to milliseconds rounded based on fractional milliseconds
        this.fixedTime = millis + fractionalMillis;
    }

    public void addMinutes(Integer deltaMinutes) {
        this.fixedTime += deltaMinutes * 60 * 1000; // Convert minutes to milliseconds
    }
}
