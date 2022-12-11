package tech.getarays.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "time")
public class Time implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    private char[] board_mac = new char[12];
    private LocalDateTime started;
    private LocalDateTime ended;

    public Time(){}

    public Time( char[] board_mac, LocalDateTime started, LocalDateTime ended) {
        this.board_mac = board_mac;
        this.started = started;
        this.ended = ended;
    }
    public Time(long id, char[] board_mac, LocalDateTime started, LocalDateTime ended) {
        this.id = id;
        this.board_mac = board_mac;
        this.started = started;
        this.ended = ended;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char[] getBoard_mac() {
        return board_mac;
    }

    public void setBoard_mac(char[] board_mac) {
        this.board_mac = board_mac;
    }

    public LocalDateTime getStarted() {
        return started;
    }

    public void setStarted(LocalDateTime started) {
        this.started = started;
    }

    public LocalDateTime getEnded() {
        return ended;
    }

    public void setEnded(LocalDateTime ended) {
        this.ended = ended;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", board_mac=" + Arrays.toString(board_mac) +
                ", started=" + started +
                ", ended=" + ended +
                '}';
    }
}
