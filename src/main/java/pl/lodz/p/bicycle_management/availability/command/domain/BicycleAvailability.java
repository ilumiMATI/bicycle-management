package pl.lodz.p.bicycle_management.availability.command.domain;

import jakarta.persistence.*;
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
    LocalDateTime lockStartTime;

    @Version
    Integer version;

    public BicycleAvailability(final BicycleNumber bicycleNumber) {
        this.bicycleNumber = bicycleNumber;
    }

    public void lockFor(UserId userId, LocalDateTime lockTime) {
        if (this.userId != null) {
            throw new BicycleAlreadyLockedException();
        }

        log.info(prefix() + "Locking for user " + userId.asString());

        this.lockStartTime = lockTime;
        this.userId = userId;
    }

    // TODO: Remember to change seconds to minutes
    public LockDuration unlock(LocalDateTime unlockTime) {
        log.info(prefix() + "Unlocking from user " + this.userId.asString());
        LockDuration lockDuration = null;
        if (this.lockStartTime != null) {
            Integer minutes = (int) this.lockStartTime.until(unlockTime, ChronoUnit.MINUTES);
            lockDuration = LockDuration.of(lockStartTime,minutes);
        }
        log.info(prefix() + "Bicycle was locked for " + lockDuration.inMinutes().toString());
        this.lockStartTime = null;
        this.userId = null;
        return lockDuration;
    }

    private String prefix() {
        return "[BicycleAvailability with bicycleNumber " + bicycleNumber.asString() + "] ";
    }

}
