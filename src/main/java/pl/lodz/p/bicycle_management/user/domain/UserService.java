package pl.lodz.p.bicycle_management.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncodingService encoder;
    private final UserWalletService userWalletService;
    private final RentalService rentalService;

    public User save(User user) {
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
        rentalService.removeRentalsForUser(id);
        userWalletService.removeWalletForUser(id);
        userRepository.remove(id);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public PageUser findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}