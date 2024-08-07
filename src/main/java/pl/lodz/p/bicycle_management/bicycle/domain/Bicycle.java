package pl.lodz.p.bicycle_management.bicycle.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "bicycles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "bicycles_bicycle_number_unique",
                        columnNames = "bicycleNumber"
                )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bicycle {
    @Id
    @SequenceGenerator(
            name = "bicycle_id_seq",
            sequenceName = "bicycle_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bicycle_id_seq"
    )
    @Column (nullable = false)
    private Integer id;

    @Embedded
    @Column (nullable = false)
    private BicycleNumber bicycleNumber;

    @Column (nullable = false)
    private String model;

    @Column (nullable = false)
    private String brand;

    @Column (nullable = false)
    private Integer batteryChargeDesign;

    @Column (nullable = true)
    private Integer batteryChargeCurrent;

    @Version
    private Integer version;

    public Bicycle(BicycleNumber bicycleNumber, String model, String brand, Integer batteryChargeDesign) {
        this.bicycleNumber = bicycleNumber;
        this.model = model;
        this.brand = brand;
        this.batteryChargeDesign = batteryChargeDesign;
    }
}
