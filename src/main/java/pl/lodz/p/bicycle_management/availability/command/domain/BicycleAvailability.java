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
import lombok.extern.java.Log;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(
        name = "bicycle_availabilities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "bicycle_availabilities_bicycle_number_unique",
                        columnNames = "bicycleNumber"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Log
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

    @Embedded
    @Column(nullable = false)
    BicycleNumber bicycleNumber;

    @Column(nullable = true)
    @Embedded
    UserId userId;

    @Column(nullable = true)
    LocalDateTime lockTime;

    @Version
    Integer version;

    public BicycleAvailability(final BicycleNumber bicycleNumber) {
        this.bicycleNumber = bicycleNumber;
    }

    public void lockFor(UserId userId) {
        if (this.userId != null) {
            throw new BicycleAlreadyLockedException();
        }

        log.info(prefix() + "Locking for user " + userId.asString());

        this.lockTime = LocalDateTime.now();
        this.userId = userId;
    }

    public Integer unlock() {
        log.info(prefix() + "Unlocking from user " + this.userId.asString());
        Integer minutes = null;
        if (this.lockTime != null) {
            minutes = (int) lockTime.until(LocalDateTime.now(), ChronoUnit.SECONDS);
        }
        this.lockTime = null;
        this.userId = null;
        return minutes;
    }

    private String prefix() {
        return "[BicycleAvailability with bicycleNumber " + bicycleNumber.asString() + "] ";
    }

}
