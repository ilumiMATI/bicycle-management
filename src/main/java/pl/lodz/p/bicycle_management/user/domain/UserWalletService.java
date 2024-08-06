package pl.lodz.p.bicycle_management.user.domain;

public interface UserWalletService {
    void createWalletForUser(Integer id);
    void removeWalletForUser(Integer id);
}
