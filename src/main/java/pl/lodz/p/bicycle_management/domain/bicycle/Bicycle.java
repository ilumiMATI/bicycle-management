package pl.lodz.p.bicycle_management.domain.bicycle;

import jakarta.persistence.*;
import lombok.*;
import pl.lodz.p.annotations.ddd.AggregateRoot;
import pl.lodz.p.bicycle_management.domain.bicycle.type.BicycleType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "bicycles")
@Entity
@AggregateRoot
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "bicycleTypeId")
    private BicycleType bicycleType;

    @Column (nullable = false)
    private Integer batteryChargeDesign;

    @Column (nullable = false)
    private Integer batteryChargeCurrent;
}
