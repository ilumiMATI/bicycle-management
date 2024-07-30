package pl.lodz.p.bicycle_management;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.user.infrastructure.web.UserDto;
import pl.lodz.p.bicycle_management.user.infrastructure.web.UserDtoMapper;
import pl.lodz.p.bicycle_management.user.application.UserService;

@RequiredArgsConstructor
@Component
public class DefaultUsers implements CommandLineRunner {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    private final UserDto users[] = {
            new UserDto(null, "CasualBikeEnjoyer","casual@casual.com"),
            new UserDto(null, "FastMikeBike","Mike@gmail.com")
    };

    @Override
    public void run(String... args) throws Exception {
        for (UserDto user : users) {
            userService.addUser(userDtoMapper.toDomain(user));
        }
    }
}
