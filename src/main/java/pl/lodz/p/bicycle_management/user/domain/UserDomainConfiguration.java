package pl.lodz.p.bicycle_management.user.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("user.domain.configuration")
public class UserDomainConfiguration {
//    @Bean
//    public RentalService rentalServiceInUserContext(pl.lodz.p.bicycle_management.rental.command.application.RentalService rentalService) {
//        return new RentalServiceAdapter(rentalService);
//    }
}
