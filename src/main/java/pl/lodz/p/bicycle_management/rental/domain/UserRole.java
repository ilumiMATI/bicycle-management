package pl.lodz.p.bicycle_management.rental.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN ("ADMIN"),
    VIP ("VIP"),
    USER ("USER");

    private final String value;
}
