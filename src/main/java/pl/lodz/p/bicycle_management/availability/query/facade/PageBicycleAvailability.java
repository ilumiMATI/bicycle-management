package pl.lodz.p.bicycle_management.availability.query.facade;

import pl.lodz.p.bicycle_management.availability.command.domain.BicycleAvailability;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class PageBicycleAvailability implements Serializable {

    List<BicycleAvailability> availabilities;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}