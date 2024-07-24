package pl.lodz.p.bicycle_management.user.external.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.bicycle_management.user.domain.User;

public interface JpaUserRepository extends JpaRepository<User,Integer> {
}
