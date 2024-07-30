package pl.lodz.p.bicycle_management.rental.domain;

import jakarta.persistence.*;
import lombok.*;
import pl.lodz.p.bicycle_management.annotations.ddd.AggregateRoot;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "rents")
public class Rent {
    @Id
    @SequenceGenerator(
            name = "rent_id_seq",
            sequenceName = "rent_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rent_id_seq"
    )
    @Column(name = "rentId")
    private Integer id;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    @Column(name = "bicycleId", nullable = false)
    private Integer bicycleId;

    private LocalDateTime timeRented;

}
