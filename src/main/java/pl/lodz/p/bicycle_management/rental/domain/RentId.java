package pl.lodz.p.bicycle_management.rental.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Embeddable
@NoArgsConstructor
@Getter
public class RentId {
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
    Integer id;

    public RentId(Integer id) {

        this.id = id;
    }
}
