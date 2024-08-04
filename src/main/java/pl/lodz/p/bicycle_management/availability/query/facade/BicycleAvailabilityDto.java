package pl.lodz.p.bicycle_management.availability.query.facade;

public record BicycleAvailabilityDto(
        String bicycleId,
        Integer userId,
        Integer version
) {}
