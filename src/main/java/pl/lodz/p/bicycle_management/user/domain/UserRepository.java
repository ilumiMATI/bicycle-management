package pl.lodz.p.bicycle_management.user.domain;

import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.user.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    User save(User user);
    Optional<User> findById(Integer id);
    List<User> findAll();
    User update(User user);
    void delete(Integer id);
}
