package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Personal;

import java.util.Optional;

@Repository
public interface PersonalRepository extends UserRepository<Personal> {

    boolean existsByEmail(String email);
    Optional<Personal> findByEmail(String email);
}
