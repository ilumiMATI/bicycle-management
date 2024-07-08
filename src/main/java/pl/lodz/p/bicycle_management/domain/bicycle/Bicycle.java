package pl.lodz.p.bicycle_management.domain.bicycle;

import lombok.Data;
import pl.lodz.p.annotations.architecture.PrimaryPort;
import pl.lodz.p.annotations.ddd.AggregateRoot;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicycle bicycle = (Bicycle) o;
        return Objects.equals(id, bicycle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
