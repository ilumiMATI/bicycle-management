package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import org.mapstruct.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RentIdMapping {
    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    public @interface ToRentId {}

    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    public @interface FromRentId {}
}
