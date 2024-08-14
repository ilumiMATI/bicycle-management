package pl.lodz.p.bicycle_management.clock;

import java.time.LocalDateTime;

public class SystemClock implements Clock {

    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public void addMinutes(Integer timeInMinutes) {
        return;
    }

    @Override
    public void setDateTime(LocalDateTime newDateTime) {
        return;
    }
}