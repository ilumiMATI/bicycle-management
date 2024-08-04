package pl.lodz.p.bicycle_management.bicycle.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "bicycles")
@Entity
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
    @Column(name = "bicycleId")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "bicycleTypeId")
    private BicycleType bicycleType;

    @Column (nullable = false)
    private Integer batteryChargeDesign;

    @Column (nullable = false)
    private Integer batteryChargeCurrent;
}
