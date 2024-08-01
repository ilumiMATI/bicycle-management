package pl.lodz.p.bicycle_management.rental.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lodz.p.bicycle_management.rental.domain.RentRepository;
import pl.lodz.p.bicycle_management.rental.infrastructure.storage.JpaRentRepository;
import pl.lodz.p.bicycle_management.rental.infrastructure.storage.RentStorageAdapter;

@Configuration
@ConfigurationProperties("rent.domain.properties")
public class RentDomainConfiguration {

    @Bean
    public RentRepository rentRepository(JpaRentRepository jpaRentRepository) {
        return new RentStorageAdapter(jpaRentRepository);
    }

}
