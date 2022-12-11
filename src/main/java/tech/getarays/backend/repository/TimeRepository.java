package tech.getarays.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.getarays.backend.model.Time;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

    @Query(value = "SELECT * FROM time WHERE board_mac = ?1",
            nativeQuery = true)
    List<Time> getAllByMac(String boardMac);

    @Query(value = "SELECT board_mac " +
            "FROM data " +
            "WHERE id = " +
                "(SELECT MIN(id)" +
                "FROM data " +
                "WHERE time_stamp >= ?1)",
            nativeQuery = true)
    String getMacByTime(LocalDateTime time);
}
