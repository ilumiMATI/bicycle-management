package pl.lodz.p.bicycle_management.rental.command.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "user_rentals",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_rentals_user_id_unique",
                        columnNames = "userId"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Log
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
    @Column(name = "bicycle_number", nullable = true)
    List<String> bicycles = new ArrayList<>();

    @Version
    Integer version;

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
        log.info(this.toString() + " Renting bike:" + bicycleNumber);
        rentingPolicy.rentBicycle( this, bicycleNumber);
    }

    public void returnBike(String bicycleNumber) {
        if (returningPolicy == null) {
            throw new IllegalStateException("Renting policy not set");
        }
        log.info(this.toString() + " Returning bike:" + bicycleNumber);
        returningPolicy.returnBicycle(this, bicycleNumber);
    }

}
