package tech.getarays.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity // An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
@Table(name = "boards") // In most cases, the name of the table in the database and the name of the entity won't be the same.
public class Board implements Serializable {

    @Id // indicating the member field below is the primary key of the current entity.
    // @Column annotation to mention the details of a column in the table.
    // @Column(name="mac_address", length=12, nullable=false, unique=true)
    private char[] mac_address = new char[12];
    private char[] name = new char[255];
    private long user_id;
    private boolean is_online;

    public Board() {}

    public Board(char[] mac_address, char[] name, long user_id, boolean is_online) {
        this.mac_address = mac_address;
        this.name = name;
        this.user_id = user_id;
        this.is_online = is_online;
    }

    public char[] getMac_address() {
        return mac_address;
    }

    public void setMac_address(char[] mac_address) {
        this.mac_address = mac_address;
    }

    public char[] getName() {
        return name;
    }

    public void setName(char[] name) {
        this.name = name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public boolean isIs_online() {
        return is_online;
    }

    public void setIs_online(boolean is_online) {
        this.is_online = is_online;
    }

    @Override
    public String toString() {
        return "Board{" +
                "mac_address=" + Arrays.toString(mac_address) +
                ", name=" + Arrays.toString(name) +
                ", user_id=" + user_id +
                ", is_online=" + is_online +
                '}';
    }
}