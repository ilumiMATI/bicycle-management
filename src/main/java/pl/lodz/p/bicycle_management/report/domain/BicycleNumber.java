package pl.lodz.p.bicycle_management.report.domain;

public record BicycleNumber(String bicycleNumber) {
    public static BicycleNumber of(String bicycleNumber) {
        return new BicycleNumber(bicycleNumber);
    }
    public String asString() {
        return bicycleNumber;
    }
}
