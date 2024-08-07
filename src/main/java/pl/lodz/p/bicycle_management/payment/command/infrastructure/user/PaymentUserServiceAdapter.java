package pl.lodz.p.bicycle_management.payment.command.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.lodz.p.bicycle_management.payment.command.application.UserService;
import pl.lodz.p.bicycle_management.payment.command.domain.User;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;

@Service
@RequiredArgsConstructor
public class PaymentUserServiceAdapter implements UserService {
    private final pl.lodz.p.bicycle_management.user.domain.UserService userService;
    private final UserPaymentUserServiceMapper userPaymentUserServiceMapper;

    @Override
    public User getUser(UserId userId) {
        return userPaymentUserServiceMapper.toPaymentContext(userService.findById(userId.userId()));
    }
}
