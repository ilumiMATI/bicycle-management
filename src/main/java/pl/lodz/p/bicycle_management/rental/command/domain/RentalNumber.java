package pl.lodz.p.bicycle_management.rental.command.domain;

public record RentalNumber(String rentalNumber) {

    public static RentalNumber of(String rentalNumber) {
        return new RentalNumber(rentalNumber);
    }

    public String asString() {
        return rentalNumber;
    }
}
