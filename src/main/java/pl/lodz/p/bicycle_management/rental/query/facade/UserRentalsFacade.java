package pl.lodz.p.bicycle_management.rental.query.facade;


import pl.lodz.p.bicycle_management.rental.command.domain.UserId;
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

    private final JpaQueryUserRentalsRepository jpaQueryUserRentalsRepository;
    private final UserRentalsDtoMapper userRentalsDtoMapper;
    private final PageUserRentalsDtoMapper pageUserRentalsDtoMapper;

    public UserRentalsDto findByUserId(final UserId userId) {
        final Optional<UserRentals> maybeReservation = jpaQueryUserRentalsRepository.findByUserId(userId);
        return userRentalsDtoMapper.toDto(maybeReservation.orElseThrow(UserRentalsDtoNotFoundException::new));
    }

    public PageUserRentalsDto findAll(final Pageable pageable) {
        Page<UserRentals> pageOfUserRentalsEntity = jpaQueryUserRentalsRepository.findAll(pageable);
        List<UserRentals> userRentalsOnCurrentPage = new ArrayList<>(pageOfUserRentalsEntity.getContent());

        final PageUserRentals pageReservation = new PageUserRentals(
                userRentalsOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfUserRentalsEntity.getTotalPages(),
                pageOfUserRentalsEntity.getTotalElements()
        );
        return pageUserRentalsDtoMapper.toPageDto(pageReservation);
    }
}
