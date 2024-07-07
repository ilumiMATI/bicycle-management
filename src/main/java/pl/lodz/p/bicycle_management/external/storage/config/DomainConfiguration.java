package pl.lodz.p.bicycle_management.external.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lodz.p.bicycle_management.api.bicycle.BicycleApiService;
import pl.lodz.p.bicycle_management.api.bicycle.BicycleDtoMapper;
import pl.lodz.p.bicycle_management.domain.BicycleRepository;
import pl.lodz.p.bicycle_management.external.storage.bicycle.BicycleMemoryStorageAdapter;

@Configuration
@ConfigurationProperties("domain.properties")
public class DomainConfiguration {
    @Bean
    public BicycleRepository bicycleRepository(BicycleDtoMapper bicycleDtoMapper) {
        return new BicycleMemoryStorageAdapter(bicycleDtoMapper);
    }

    @Bean
    public BicycleApiService bicycleApiService(BicycleRepository bicycleRepository) {
        return new BicycleApiService(bicycleRepository);
    }

}
