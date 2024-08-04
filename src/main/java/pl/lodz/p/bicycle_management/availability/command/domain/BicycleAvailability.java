package pl.lodz.p.bicycle_management.availability.command.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "bicycle_id_number_unique",
                        columnNames = "bicycleId"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BicycleAvailability {
    @Id
    @SequenceGenerator(
            name = "bicycle_availability_id_seq",
            sequenceName = "bicycle_availability_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bicycle_availability_id_seq"
    )
    Integer id;
    @Column(nullable = false)
    @Embedded
    BicycleId bicycleId;
    @Column(nullable = true)
    @Embedded
    UserId userId;
    @Version
    Integer version;

    public BicycleAvailability(final BicycleId bicycleId) {
        this.bicycleId = bicycleId;
    }

    public void lockFor(UserId userId) {
        if (this.userId != null) {
            throw new BicycleAlreadyLockedException();
        }
        this.userId = userId;
    }

    public void unlock() {
        this.userId = null;
    }

}
