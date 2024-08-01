package pl.lodz.p.bicycle_management.rental.domain;

public class RentAlreadyExistsException extends RuntimeException {
    public RentAlreadyExistsException() {
        super("Rent already exists");
    }
}
