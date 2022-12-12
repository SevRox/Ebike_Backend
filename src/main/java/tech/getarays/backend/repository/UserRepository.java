package tech.getarays.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarays.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
