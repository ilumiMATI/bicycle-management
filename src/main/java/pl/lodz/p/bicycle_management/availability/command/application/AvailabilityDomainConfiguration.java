package pl.lodz.p.bicycle_management.availability.command.application;

import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAvailabilityRepository;
import pl.lodz.p.bicycle_management.availability.command.infrastructure.storage.BicycleAvailabilityStorageAdapter;
import pl.lodz.p.bicycle_management.availability.command.infrastructure.storage.JpaBicycleAvailabilityRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties("availability.domain.properties")
public class AvailabilityDomainConfiguration {

    @Bean
    public BicycleAvailabilityRepository bicycleAvailabilityRepository(JpaBicycleAvailabilityRepository jpaBicycleAvailabilityRepository) {
        return new BicycleAvailabilityStorageAdapter(jpaBicycleAvailabilityRepository);
    }

}
