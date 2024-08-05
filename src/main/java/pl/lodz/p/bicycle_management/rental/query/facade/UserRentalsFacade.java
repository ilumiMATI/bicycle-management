package pl.lodz.p.bicycle_management.rental.query.facade;


import pl.lodz.p.bicycle_management.rental.command.domain.UserRentals;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public class UserRentalsFacade {

    private final JpaQueryUserRentalsRepository repository;
    private final UserRentalsDtoMapper mapper;
    private final PageUserRentalsDtoMapper pageMapper;

    public UserRentalsDto findByUserId(final Integer userId) {
        final Optional<UserRentals> maybeReservation = repository.findByUserId(userId);
        return mapper.toDto(maybeReservation.orElseThrow(UserRentalsDtoNotFoundException::new));
    }

    public PageUserRentalsDto findAll(final Pageable pageable) {
        Page<UserRentals> pageOfUserRentalsEntity = repository.findAll(pageable);
        List<UserRentals> userRentalsOnCurrentPage = new ArrayList<>(pageOfUserRentalsEntity.getContent());

        final PageUserRentals pageReservation = new PageUserRentals(
                userRentalsOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfUserRentalsEntity.getTotalPages(),
                pageOfUserRentalsEntity.getTotalElements()
        );
        return pageMapper.toPageDto(pageReservation);
    }
}
