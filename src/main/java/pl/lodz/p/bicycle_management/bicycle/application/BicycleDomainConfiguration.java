package pl.lodz.p.bicycle_management.bicycle.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleRepository;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.storage.BicycleStorageAdapter;
import pl.lodz.p.bicycle_management.bicycle.infrastructure.storage.JpaBicycleRepository;

@Configuration
@ConfigurationProperties("bicycle.domain.properties")
public class BicycleDomainConfiguration {
    @Bean
    public BicycleRepository bicycleRepository(JpaBicycleRepository jpaBicycleRepository) {
        return new BicycleStorageAdapter(jpaBicycleRepository);
    }

//    @Bean
//    public BicycleService bicycleService(BicycleRepository bicycleRepository) {
//        return new BicycleService(bicycleRepository);
//    }

}
