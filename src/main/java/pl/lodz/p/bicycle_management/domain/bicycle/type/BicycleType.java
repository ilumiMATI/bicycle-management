package pl.lodz.p.bicycle_management.domain.bicycle.type;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "bicycleTypes")
@Entity
public class BicycleType {

    @Id
    @SequenceGenerator(
            name = "bicycle_type_id_seq",
            sequenceName = "bicycle_type_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bicycle_type_id_seq"
    )
    @Column(name = "bicycleTypeId")
    private Integer id;

    @Column (nullable = false)
    private String brand;

    @Column (nullable = false)
    private String model;

    @Column (nullable = false)
    private Integer productionYear;
}
