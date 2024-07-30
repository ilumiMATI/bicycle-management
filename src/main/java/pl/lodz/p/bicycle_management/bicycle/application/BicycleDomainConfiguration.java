package pl.lodz.p.bicycle_management.bicycle.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleRepository;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleTypeRepository;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.storage.BicycleDatabaseStorageAdapter;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.storage.JpaBicycleRepository;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.storage.BicycleTypeDatabaseStorageAdapter;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.storage.JpaBicycleTypeRepository;

@Configuration
@ConfigurationProperties("bicycle.domain.properties")
public class BicycleDomainConfiguration {
    @Bean
    public BicycleRepository bicycleRepository(JpaBicycleRepository jpaBicycleRepository) {
        return new BicycleDatabaseStorageAdapter(jpaBicycleRepository);
    }

    @Bean
    public BicycleService bicycleService(BicycleRepository bicycleRepository) {
        return new BicycleService(bicycleRepository);
    }

    @Bean
    public BicycleTypeRepository bicycleTypeRepository(JpaBicycleTypeRepository jpaBicycleTypeRepository) {
        return new BicycleTypeDatabaseStorageAdapter(jpaBicycleTypeRepository);
    }

    @Bean
    public BicycleTypeService bicycleTypeService(BicycleTypeRepository bicycleTypeRepository) {
        return new BicycleTypeService(bicycleTypeRepository);
    }

}
