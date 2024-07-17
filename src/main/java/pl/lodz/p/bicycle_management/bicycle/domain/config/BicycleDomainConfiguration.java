package pl.lodz.p.bicycle_management.bicycle.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleService;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleRepository;
import pl.lodz.p.bicycle_management.bicycle.domain.type.BicycleTypeRepository;
import pl.lodz.p.bicycle_management.bicycle.domain.type.BicycleTypeService;
import pl.lodz.p.bicycle_management.bicycle.external.storage.BicycleDatabaseStorageAdapter;
import pl.lodz.p.bicycle_management.bicycle.external.storage.JpaBicycleRepository;
import pl.lodz.p.bicycle_management.bicycle.external.storage.type.BicycleTypeDatabaseStorageAdapter;
import pl.lodz.p.bicycle_management.bicycle.external.storage.type.JpaBicycleTypeRepository;

@Configuration
@ConfigurationProperties("domain.properties")
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
