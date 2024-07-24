package pl.lodz.p.bicycle_management.rental.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("rent.domain.properties")
public class RentDomainConfiguration {

}
