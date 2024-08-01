package pl.lodz.p.bicycle_management.rental.domain;

public class MethodNotAllowedException extends RuntimeException {
    public MethodNotAllowedException() {
        super("Method not allowed");
    }
}
