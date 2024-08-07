package pl.lodz.p.bicycle_management;

import pl.lodz.p.bicycle_management.user.domain.User;
import pl.lodz.p.bicycle_management.user.domain.UserRole;
import pl.lodz.p.bicycle_management.user.domain.UserService;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log
public class DefaultUsers implements CommandLineRunner {

    private final UserService userService;

    public DefaultUsers(UserService userService) {
        this.userService = userService;
    }

    private final User adminUser = new User(
            "admin@gmail.com",
            "Admin",
            "password",
            UserRole.ADMIN
    );

    private final User vipUser = new User(
            "vip@gmail.com",
            "Vip",
            "password",
            UserRole.VIP
    );

    private final User regularUser = new User(
            "user@gmail.com",
            "User",
            "password",
            UserRole.USER
    );

    @Override
    public void run(String... args) {
        try {
            addUser(adminUser);
            addUser(vipUser);
            addUser(regularUser);
        } catch (Exception ex) {
            log.warning(ex.getMessage());
        }
    }

    private void addUser(User user) {
        userService.save(user);
    }
}
