package pl.lodz.p.bicycle_management.report.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Random;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentalNumber implements Serializable {
    String rentalNumber;

    public static RentalNumber random() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append((char) random.nextInt(65,91));
        }
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(0,9));
        }
        return new RentalNumber(sb.toString());
    }

    public static RentalNumber of(String rentalNumber) {
        return new RentalNumber(rentalNumber);
    }

    public String asString() {
        return rentalNumber;
    }
}
