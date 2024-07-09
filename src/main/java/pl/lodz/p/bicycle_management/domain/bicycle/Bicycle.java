package pl.lodz.p.bicycle_management.domain.bicycle;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.lodz.p.annotations.architecture.PrimaryPort;
import pl.lodz.p.annotations.ddd.AggregateRoot;

import java.util.Objects;

@EqualsAndHashCode(of = "id")
@Data
@AggregateRoot
public class Bicycle {
    private Integer id;
    private String brand;
    private String model;
    private Integer productionYear;
    private Integer maxSpeed;
    private Integer batteryChargeDesign;
    private Integer batteryChargeCurrent;

    @PrimaryPort
    public void setBatteryCharge(Integer batteryCharge) {
        batteryChargeCurrent = batteryCharge;
        if (batteryCharge > batteryChargeDesign) {
            batteryChargeDesign = batteryCharge;
        }
    }

    @PrimaryPort
    public Double getBatteryPercentage() {
        return batteryChargeCurrent * 100.0 / batteryChargeDesign;
    }
}
