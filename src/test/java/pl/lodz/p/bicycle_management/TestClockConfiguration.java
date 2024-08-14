package pl.lodz.p.bicycle_management;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import pl.lodz.p.bicycle_management.clock.Clock;
import pl.lodz.p.bicycle_management.clock.FakeClock;

@TestConfiguration
public class TestClockConfiguration {

    @Bean
    @Primary
    public Clock fakeClock() {
        return new FakeClock(1723629600000L);
    }
}