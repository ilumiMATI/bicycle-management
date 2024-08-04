package pl.lodz.p.bicycle_management.availability.query.facade;

public record BicycleAvailabilityDto(
        String bicycleNumber,
        Integer userId,
        Integer version
) {}
