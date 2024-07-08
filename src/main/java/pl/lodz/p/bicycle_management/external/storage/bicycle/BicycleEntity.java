package pl.lodz.p.bicycle_management.external.storage.bicycle;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class BicycleEntity {
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
    private Integer id;

    @Column (nullable = false)
    private String brand;

    @Column (nullable = false)
    private String model;

    @Column (nullable = false)
    private Integer productionYear;

    @Column (nullable = false)
    private Integer maxSpeed;

    @Column (nullable = false)
    private Integer batteryChargeDesign;

    @Column (nullable = false)
    private Integer batteryChargeCurrent;

}
