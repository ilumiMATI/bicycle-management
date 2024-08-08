package pl.lodz.p.bicycle_management.report.domain;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class PageRentalReport implements Serializable {
    List<RentalReport> reports;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
