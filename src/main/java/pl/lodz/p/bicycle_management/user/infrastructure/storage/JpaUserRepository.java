package pl.lodz.p.bicycle_management.user.infrastructure.storage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
    Page<UserEntity> findAllByNameContainingIgnoreCase(String userName,Pageable pageable);
}