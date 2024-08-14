package pl.lodz.p.bicycle_management.clock;

import java.time.LocalDateTime;

public interface Clock {

    LocalDateTime getCurrentDateTime();

    void addMinutes(Integer timeInMinutes);

    void setDateTime(LocalDateTime newDateTime);
}
