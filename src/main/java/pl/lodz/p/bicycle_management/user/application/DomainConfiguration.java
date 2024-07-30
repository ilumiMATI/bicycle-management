package pl.lodz.p.bicycle_management.user.application;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lodz.p.bicycle_management.user.domain.UserRepository;
import pl.lodz.p.bicycle_management.user.infrastructure.storage.JpaUserRepository;
import pl.lodz.p.bicycle_management.user.infrastructure.storage.UserEntityMapper;
import pl.lodz.p.bicycle_management.user.infrastructure.storage.UserStorageAdapter;

@Configuration
@ConfigurationProperties("user.domain.properties")
public class DomainConfiguration {

//    @Bean
//    public Clock clock() {
//        return Clock.systemDefaultZone();
//    }

    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository, UserEntityMapper mapper) {
        return new UserStorageAdapter(jpaUserRepository, mapper);
    }

//    @Bean
//    public UserService userService(UserRepository userRepository, EncodingService encoder, Clock clock)  {
//        return new UserService(userRepository, encoder, clock);
//    }

}
