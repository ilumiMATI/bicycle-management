package pl.lodz.p.bicycle_management.rental.command.domain;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BicycleNumber {
    private String bicycleNumber;

    public static BicycleNumber of(String bicycleNumber) {
        return new BicycleNumber(bicycleNumber);
    }
    public String asString() {
        return bicycleNumber;
    }
}
