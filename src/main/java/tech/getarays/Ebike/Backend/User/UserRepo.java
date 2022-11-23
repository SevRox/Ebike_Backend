package tech.getarays.Ebike.Backend.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "WITH deleted AS (DELETE FROM users u WHERE u.login = ?1 Returning *) SELECT count(*) FROM deleted",
            nativeQuery = true)
    Long deleteBylogin(String login);

    List<User> findAllBylogin(String login);
//    Long deleteByLogin(String login);
}
