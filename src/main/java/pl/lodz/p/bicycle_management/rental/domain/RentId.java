package pl.lodz.p.bicycle_management.rental.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class RentId implements Serializable {
    Integer id;

    public RentId(Integer id) {
        this.id = id;
    }
}
