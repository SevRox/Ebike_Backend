package tech.getarays.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.getarays.backend.model.Data;

import java.util.List;


@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

    @Query(value = "SELECT * " +
            "FROM data " +
            "WHERE board_mac = ?1 " +
            "AND time_stamp = (SELECT MAX(time_stamp) FROM data WHERE board_mac = ?1)",
            nativeQuery = true)
    Data getNewest(String board_mac);

    @Query(value = "SELECT min(id) FROM data WHERE board_mac = ?1",
            nativeQuery = true)
    Long getIdByMac(char[] board_mac);

    @Query(value = "SELECT * FROM data d, time t " +
            "WHERE d.board_mac = ?2 " +
            "AND t.id = ?1 " +
            "AND time_stamp >= t.started " +
            "AND time_stamp <= t.ended " +
            "ORDER BY d.time_stamp",
            nativeQuery = true)
    List<Data> dataFromTimeStamps(Long timeId, String boardMac);

    @Query(value = "SELECT board_mac " +
            "FROM data " +
            "WHERE time_stamp = (SELECT MAX(time_stamp) FROM data)",
            nativeQuery = true)
    String lastSelectedBoard();
}