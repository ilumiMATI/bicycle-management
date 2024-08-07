package pl.lodz.p.bicycle_management.user.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log
public class UserService {

    private final UserRepository userRepository;
    private final EncodingService encoder;
    private final UserWalletService userWalletService;
    private final RentalService rentalService;

    public User save(User user) {
        log.info(prefix() + "Saving user: " + user.toString());
        User createdUser = userRepository.save(
                user.withPassword(
                        encoder.encode(user.getPassword())
                )
        );
        rentalService.createRentalsForUser(createdUser.getId());
        userWalletService.createWalletForUser(createdUser.getId());
        return createdUser;
    }
    public void remove(Integer id) {
        log.info(prefix() + "Removing user with id " + id.toString());
        rentalService.removeRentalsForUser(id);
        userWalletService.removeWalletForUser(id);
        userRepository.remove(id);
    }

    public void update(User user) {
        log.info(prefix() + "Updating user with id " + user.getId().toString());
        userRepository.update(user);
    }

    public User findByEmail(String email) {
        log.info(prefix() + "Finding user by email  " + email);
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public User findById(Integer id) {
        log.info(prefix() + "Finding user by id  " + id);
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public PageUser findAll(Pageable pageable) {
        log.info(prefix() + "Finding users");
        return userRepository.findAll(pageable);
    }

    private String prefix() {
        return "[UserService] ";
    }
}