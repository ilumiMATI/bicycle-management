package pl.lodz.p.bicycle_management.rental.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public record BicycleId(Integer id) {
}
