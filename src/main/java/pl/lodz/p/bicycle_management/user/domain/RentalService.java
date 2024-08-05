package pl.lodz.p.bicycle_management.user.domain;

public interface RentalService {
    void createRentalsForUser(Integer id);
    void removeRentalsForUser(Integer id);
}
