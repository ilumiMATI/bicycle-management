package pl.lodz.p.bicycle_management.rental.domain;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class PageRent implements Serializable {
    List<Rent> rents;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
