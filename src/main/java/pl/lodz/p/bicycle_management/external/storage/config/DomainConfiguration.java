package pl.lodz.p.bicycle_management.external.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lodz.p.bicycle_management.domain.bicycle.BicycleService;
import pl.lodz.p.bicycle_management.domain.bicycle.BicycleRepository;
import pl.lodz.p.bicycle_management.domain.bicycle.type.BicycleTypeRepository;
import pl.lodz.p.bicycle_management.domain.bicycle.type.BicycleTypeService;
import pl.lodz.p.bicycle_management.external.storage.bicycle.BicycleDatabaseStorageAdapter;
import pl.lodz.p.bicycle_management.external.storage.bicycle.JpaBicycleRepository;
import pl.lodz.p.bicycle_management.external.storage.bicycle.type.BicycleTypeDatabaseStorageAdapter;
import pl.lodz.p.bicycle_management.external.storage.bicycle.type.JpaBicycleTypeRepository;

@Configuration
@ConfigurationProperties("domain.properties")
public class DomainConfiguration {
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
