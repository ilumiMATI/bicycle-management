package pl.lodz.p.bicycle_management.user.infrastructure.storage;


import org.springframework.stereotype.Repository;
import pl.lodz.p.bicycle_management.user.domain.PageUser;
import pl.lodz.p.bicycle_management.user.domain.User;
import pl.lodz.p.bicycle_management.user.domain.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.lodz.p.bicycle_management.user.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log
@Repository
public class UserStorageAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(final User user) {
        try {
            User saved = jpaUserRepository.save(user);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("User " + user.getEmail() + " already exits in db");
            throw new UserAlreadyExistsException();
        }
    }

    @Override
    public void update(final User user) {
        jpaUserRepository.findById(user.getId()).ifPresent(userEntity -> jpaUserRepository.save(user));
    }

    @Override
    public void remove(final Integer id) {
        jpaUserRepository.findById(id).ifPresent(userEntity -> jpaUserRepository.deleteById(id));
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(final Integer id) {
        return jpaUserRepository.findById(id);
    }

    @Override
    public PageUser findAll(final Pageable pageable) {
        Page<User> pageOfUsersEntity = jpaUserRepository.findAll(pageable);
        List<User> usersOnCurrentPage = pageOfUsersEntity.getContent();
        return new PageUser(
                usersOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfUsersEntity.getTotalPages(),
                pageOfUsersEntity.getTotalElements()
        );
    }
}
