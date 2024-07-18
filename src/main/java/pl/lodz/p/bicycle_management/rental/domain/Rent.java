package pl.lodz.p.bicycle_management.rental.domain;

import jakarta.persistence.*;
import lombok.*;
import pl.lodz.p.bicycle_management.annotations.ddd.AggregateRoot;
import pl.lodz.p.bicycle_management.bicycle.domain.Bicycle;
import pl.lodz.p.bicycle_management.user.domain.User;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "rents")
@Entity
@AggregateRoot
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bicycleId")
    private Bicycle bicycle;

    private LocalDateTime timeRented;

}
