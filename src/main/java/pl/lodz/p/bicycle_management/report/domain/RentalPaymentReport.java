package pl.lodz.p.bicycle_management.report.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "rental_payment_reports",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "rental_payment_reports_rental_number_unique",
                        columnNames = "rentalNumber"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RentalPaymentReport {
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
    Money paidPrice;

    public RentalPaymentReport(RentalNumber rentalNumber, UserId userId, Money paidPrice) {
        this.rentalNumber = rentalNumber;
        this.userId = userId;
        this.paidPrice = paidPrice;
    }
}
