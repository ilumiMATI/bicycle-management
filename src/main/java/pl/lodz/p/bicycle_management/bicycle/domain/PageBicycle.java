package pl.lodz.p.bicycle_management.bicycle.domain;

import lombok.Value;

import java.util.List;

@Value
public class PageBicycle {
    List<Bicycle> bicycles;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
