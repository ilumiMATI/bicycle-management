package pl.lodz.p.bicycle_management.clock;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log
public class ClockService {
    private final Clock clock;

    public LocalDateTime getCurrentDateTime() {
        log.info("[CLOCK] using " + clock.toString());
        return clock.getCurrentDateTime();
    }

    public void addMinutes(Integer timeInMinutes) {
        clock.addMinutes(timeInMinutes);
    }

    public void setDateTime(LocalDateTime newDateTime) {
        clock.setDateTime(newDateTime);
    }
}
