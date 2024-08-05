package pl.lodz.p.bicycle_management.rental.command.application;

import pl.lodz.p.bicycle_management.rental.command.infrastructure.authentication.RentalAuthenticationFacade;
import pl.lodz.p.bicycle_management.rental.command.infrastructure.authentication.UserRentalAuthenticationMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("rental.domain.properties")
public class RentalDomainConfiguration {

//    @Bean
//    public UserRentalsRepository userRentalsRepository(JpaUserRentalsRepository jpaUserRentalsRepository) {
//        return new UserRentalsStorageAdapter(jpaUserRentalsRepository);
//    }
//    @Bean
//    public AuthenticationService authenticationServiceInRentalContext(
//            pl.lodz.p.bicycle_management.user.domain.UserService userService,
//            UserRentalAuthenticationMapper userRentalAuthenticationMapper
//    ) {
//        return new RentalAuthenticationFacade(userService, userRentalAuthenticationMapper);
//    }
}
