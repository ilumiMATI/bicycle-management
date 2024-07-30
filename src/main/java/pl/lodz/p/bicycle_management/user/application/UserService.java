package pl.lodz.p.bicycle_management.user.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.user.domain.User;
import pl.lodz.p.bicycle_management.user.domain.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    final private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }
}
