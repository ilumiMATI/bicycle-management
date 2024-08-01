package pl.lodz.p.bicycle_management.rental.domain;

import lombok.Value;

import java.util.Random;

@Value
public class RentNumber {
    String value;

    public RentNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            sb.append((char) random.nextInt(65,91));
        }
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(0,9));
        }
        this.value = sb.toString();
    }

    public RentNumber(String value) {
        this.value = value;
    }
}
