package pl.lodz.p.bicycle_management.rental.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(
    name = "rents",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "rent_number_unique",
            columnNames = "rentNumber"
        ), @UniqueConstraint(
            name = "bicycle_id_unique",
            columnNames = "bicycleId"
        )
    }
)
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

    @Version
    private Integer version;

    @Column
    private String rentNumber;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer bicycleId;

    private LocalDateTime timeRented;

}
