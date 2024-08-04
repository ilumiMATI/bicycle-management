package pl.lodz.p.bicycle_management.rental.query.facade;

import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class PageUserRentals implements Serializable {

    List<UserRentals> rentals;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
