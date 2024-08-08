package pl.lodz.p.bicycle_management.report.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "rental_reports",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "rental_reports_rental_number_unique",
                        columnNames = "rentalNumber"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RentalReport {
    @Id
    @SequenceGenerator(
            name = "rental_report_id_seq",
            sequenceName = "rental_report_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rental_report_id_seq"
    )
    Integer id;

    @Column
    @Embedded
    RentalNumber rentalNumber;

    @Column(nullable = false)
    @Embedded
    UserId userId;

    @Column(nullable = false)
    @Embedded
    BicycleNumber bicycleNumber;

    @Column(nullable = false)
    LocalDateTime rentTime;

    @Column(nullable = false)
    LocalDateTime returnTime;

    public RentalReport(RentalNumber rentalNumber, UserId userId, BicycleNumber bicycleNumber, LocalDateTime rentTime, LocalDateTime returnTime) {
        this.rentalNumber = rentalNumber;
        this.userId = userId;
        this.bicycleNumber = bicycleNumber;
        this.rentTime = rentTime;
        this.returnTime = returnTime;
    }

    public RentalReport(UserId userId, BicycleNumber bicycleNumber, LocalDateTime rentTime, LocalDateTime returnTime) {
        this.rentalNumber = RentalNumber.random();
        this.userId = userId;
        this.bicycleNumber = bicycleNumber;
        this.rentTime = rentTime;
        this.returnTime = returnTime;
    }
}
