package pl.lodz.p.bicycle_management;

import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.bicycle.domain.BicycleNumber;

public class TestBicycleFactory {

    private static Integer counter = 0;

    public static Bicycle createBicycle() {
        return new Bicycle(
                new BicycleNumber("bike" + (++counter).toString()),
                "Test Model",
                "Test Brand",
                500
        );
    }
}
