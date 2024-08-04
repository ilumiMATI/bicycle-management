package pl.lodz.p.bicycle_management.rental.command.application;

import pl.lodz.p.bicycle_management.rental.command.domain.UserRentalsRepository;
import pl.lodz.p.bicycle_management.rental.command.infrastructure.storage.JpaUserRentalsRepository;
import pl.lodz.p.bicycle_management.rental.command.infrastructure.storage.UserRentalsStorageAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("reservation.domain.properties")
public class RentalDomainConfiguration {

    @Bean
    public UserRentalsRepository userRentalsRepository(JpaUserRentalsRepository jpaUserRentalsRepository) {
        return new UserRentalsStorageAdapter(jpaUserRentalsRepository);
    }

}
