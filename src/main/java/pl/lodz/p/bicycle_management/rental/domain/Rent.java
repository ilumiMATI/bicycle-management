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
@IdClass(RentId.class)
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
    @Column(name = "rent_id", nullable = false)
//    @AttributeOverride(name = "id", column = @Column(name = "rent_id"))
    private Integer id;

    @Version
    private Integer version;

    @Column(nullable = false)
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "rent_number"))
    private RentNumber rentNumber;

    @Column(nullable = false)
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;

    @Column(nullable = false)
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "bicycle_id"))
    private BicycleId bicycleId;

    private LocalDateTime timeRented;


    public Rent(UserId userId, BicycleId bicycleId) {
        this.rentNumber = new RentNumber();
        this.userId = userId;
        this.bicycleId = bicycleId;
    }
}
