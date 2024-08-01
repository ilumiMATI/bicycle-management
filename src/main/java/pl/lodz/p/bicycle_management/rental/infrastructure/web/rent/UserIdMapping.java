package pl.lodz.p.bicycle_management.rental.infrastructure.web.rent;

import org.mapstruct.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class UserIdMapping {
    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    public @interface ToUserId {}

    @Qualifier
    @Retention(RetentionPolicy.CLASS)
    public @interface FromUserId {}
}
