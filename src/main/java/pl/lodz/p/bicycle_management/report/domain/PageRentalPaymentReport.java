package pl.lodz.p.bicycle_management.report.domain;

import lombok.Value;

import java.util.List;

@Value
public class PageRentalPaymentReport {
    List<RentalPaymentReport> reports;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
