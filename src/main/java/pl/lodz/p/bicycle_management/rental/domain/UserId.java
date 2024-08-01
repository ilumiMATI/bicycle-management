package pl.lodz.p.bicycle_management.rental.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Integer id) {
}
