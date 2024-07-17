package pl.lodz.p.bicycle_management.user.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lodz.p.bicycle_management.user.domain.UserRepository;
import pl.lodz.p.bicycle_management.user.external.storage.JpaUserRepository;
import pl.lodz.p.bicycle_management.user.external.storage.UserDatabaseStorageAdapter;
import pl.lodz.p.user_management.user.domain.UserService;

@Configuration
@ConfigurationProperties("user.domain.properties")
public class UserDomainConfiguration {
    @Bean
    public UserRepository userRepository(JpaUserRepository jpaUserRepository) {
        return new UserDatabaseStorageAdapter(jpaUserRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
