package pl.lodz.p.bicycle_management.rental.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("rent.domain.properties")
public class RentDomainConfiguration {

}
