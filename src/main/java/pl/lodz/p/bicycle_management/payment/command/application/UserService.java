package pl.lodz.p.bicycle_management.payment.command.application;

import pl.lodz.p.bicycle_management.payment.command.domain.User;
import pl.lodz.p.bicycle_management.payment.command.domain.UserId;

public interface UserService {
    User getUser(UserId userId);
}
