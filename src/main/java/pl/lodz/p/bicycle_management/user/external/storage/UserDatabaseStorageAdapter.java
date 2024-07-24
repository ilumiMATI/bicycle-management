package pl.lodz.p.bicycle_management.user.external.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.user.domain.User;
import pl.lodz.p.bicycle_management.user.domain.UserNotFoundException;
import pl.lodz.p.bicycle_management.user.domain.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserDatabaseStorageAdapter implements UserRepository {
    final private JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public User update(User user) {
        Optional<User> optionalUser = jpaUserRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            return jpaUserRepository.save(user);
        }
        throw new UserNotFoundException();
    }

    @Override
    public void delete(Integer id) {
        jpaUserRepository.deleteById(id);
    }
}
