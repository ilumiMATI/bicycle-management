package pl.lodz.p.bicycle_management.external.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lodz.p.bicycle_management.domain.bicycle.BicycleService;
import pl.lodz.p.bicycle_management.domain.bicycle.BicycleRepository;
import pl.lodz.p.bicycle_management.external.storage.bicycle.BicycleDatabaseStorageAdapter;
import pl.lodz.p.bicycle_management.external.storage.bicycle.BicycleEntityMapper;
import pl.lodz.p.bicycle_management.external.storage.bicycle.JpaBicycleRepository;

@Configuration
@ConfigurationProperties("domain.properties")
public class DomainConfiguration {
    @Bean
    public BicycleRepository bicycleRepository(JpaBicycleRepository jpaBicycleRepository, BicycleEntityMapper bicycleEntityMapper) {
        return new BicycleDatabaseStorageAdapter(jpaBicycleRepository, bicycleEntityMapper);
    }

    @Bean
    public BicycleService bicycleApiService(BicycleRepository bicycleRepository) {
        return new BicycleService(bicycleRepository);
    }

}
