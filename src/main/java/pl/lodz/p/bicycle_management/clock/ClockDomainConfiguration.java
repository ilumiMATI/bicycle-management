package pl.lodz.p.bicycle_management.clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockDomainConfiguration {
    @Bean
    public Clock clock() {
        return new SystemClock();
    }
}
