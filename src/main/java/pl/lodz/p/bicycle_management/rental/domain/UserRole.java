package pl.lodz.p.bicycle_management.rental.domain;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN ("ADMIN"),
    VIP ("VIP"),
    USER ("USER");


    private final String value;

    UserRole(String value) {
        this.value = value;
    }

}
