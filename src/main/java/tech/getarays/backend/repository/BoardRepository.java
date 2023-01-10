package tech.getarays.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.getarays.backend.model.Board;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "SELECT * FROM boards WHERE user_id = (SELECT id FROM users WHERE email = ?1)", nativeQuery = true)
    List<Board> findBoardsByUser(String username);

    @Query(value = "WITH deleted AS (DELETE FROM boards WHERE mac_address = ?1 Returning *) SELECT count(*) FROM deleted",
            nativeQuery = true)
    Long deleteByMac(String macAddress);

}
