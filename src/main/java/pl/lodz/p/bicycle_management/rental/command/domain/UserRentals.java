package pl.lodz.p.bicycle_management.rental.command.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_id_unique",
                        columnNames = "userId"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRentals {
    @Id
    @SequenceGenerator(
            name = "user_rentals_id_seq",
            sequenceName = "user_rentals_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_rentals_id_seq"
    )
    Integer id;

    @Column
    @Embedded
    UserId userId;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_rentals_bicycles", joinColumns = @JoinColumn(name = "user_rentals_id"))
    @Column(name = "bicycle", nullable = true)
    List<String> bicycles = new ArrayList<>();

    @Version
    private Integer version;

    @Transient
    RentingPolicy rentingPolicy;

    @Transient
    ReturningPolicy returningPolicy;

    UserRentals(final UserId userId) {
        this.userId = userId;
    }

    public void rentBike(String bicycleNumber) {
        if (rentingPolicy == null) {
            throw new IllegalStateException("Renting policy not set");
        }

        rentingPolicy.rentBicycle(this, bicycleNumber);
    }

    public void returnBike(String bicycleNumber) {
        if (returningPolicy == null) {
            throw new IllegalStateException("Renting policy not set");
        }

        returningPolicy.returnBicycle(this, bicycleNumber);
    }

}
