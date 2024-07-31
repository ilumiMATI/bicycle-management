package pl.lodz.p.bicycle_management.rental.domain;

import lombok.RequiredArgsConstructor;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException() {
        super("Rent not found");
    }
}
