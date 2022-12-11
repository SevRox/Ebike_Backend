package tech.getarays.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "data")
public class Data implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private char[] board_mac = new char[12];
    private LocalDateTime time_stamp;
    private double battery_temp,
            throttle_value,
            motor_current,
            input_current,
            duty_cycle_now,
            rpm,
            input_voltage,
            amp_hours,
            amp_hours_charged,
            watt_hours,
            watt_hours_charged,
            mosfet_temp,
            motor_temp;
    private int error_code;

    protected Data(){}

    public Data(Long id, char[] board_mac, LocalDateTime time_stamp, double battery_temp, double throttle_value, double motor_current, double input_current, double duty_cycle_now, double rpm, double input_voltage, double amp_hours, double amp_hours_charged, double watt_hours, double watt_hours_charged, double mosfet_temp, double motor_temp, int error_code) {
        this.id = id;
        this.board_mac = board_mac;
        this.time_stamp = time_stamp;
        this.battery_temp = battery_temp;
        this.throttle_value = throttle_value;
        this.motor_current = motor_current;
        this.input_current = input_current;
        this.duty_cycle_now = duty_cycle_now;
        this.rpm = rpm;
        this.input_voltage = input_voltage;
        this.amp_hours = amp_hours;
        this.amp_hours_charged = amp_hours_charged;
        this.watt_hours = watt_hours;
        this.watt_hours_charged = watt_hours_charged;
        this.mosfet_temp = mosfet_temp;
        this.motor_temp = motor_temp;
        this.error_code = error_code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char[] getBoard_mac() {
        return board_mac;
    }

    public void setBoard_mac(char[] board_mac) {
        this.board_mac = board_mac;
    }

    public LocalDateTime getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(LocalDateTime time_stamp) {
        this.time_stamp = time_stamp;
    }

    public double getBattery_temp() {
        return battery_temp;
    }

    public void setBattery_temp(double battery_temp) {
        this.battery_temp = battery_temp;
    }

    public double getThrottle_value() {
        return throttle_value;
    }

    public void setThrottle_value(double throttle_value) {
        this.throttle_value = throttle_value;
    }

    public double getMotor_current() {
        return motor_current;
    }

    public void setMotor_current(double motor_current) {
        this.motor_current = motor_current;
    }

    public double getInput_current() {
        return input_current;
    }

    public void setInput_current(double input_current) {
        this.input_current = input_current;
    }

    public double getDuty_cycle_now() {
        return duty_cycle_now;
    }

    public void setDuty_cycle_now(double duty_cycle_now) {
        this.duty_cycle_now = duty_cycle_now;
    }

    public double getRpm() {
        return rpm;
    }

    public void setRpm(double rpm) {
        this.rpm = rpm;
    }

    public double getInput_voltage() {
        return input_voltage;
    }

    public void setInput_voltage(double input_voltage) {
        this.input_voltage = input_voltage;
    }

    public double getAmp_hours() {
        return amp_hours;
    }

    public void setAmp_hours(double amp_hours) {
        this.amp_hours = amp_hours;
    }

    public double getAmp_hours_charged() {
        return amp_hours_charged;
    }

    public void setAmp_hours_charged(double amp_hours_charged) {
        this.amp_hours_charged = amp_hours_charged;
    }

    public double getWatt_hours() {
        return watt_hours;
    }

    public void setWatt_hours(double watt_hours) {
        this.watt_hours = watt_hours;
    }

    public double getWatt_hours_charged() {
        return watt_hours_charged;
    }

    public void setWatt_hours_charged(double watt_hours_charged) {
        this.watt_hours_charged = watt_hours_charged;
    }

    public double getMosfet_temp() {
        return mosfet_temp;
    }

    public void setMosfet_temp(double mosfet_temp) {
        this.mosfet_temp = mosfet_temp;
    }

    public double getMotor_temp() {
        return motor_temp;
    }

    public void setMotor_temp(double motor_temp) {
        this.motor_temp = motor_temp;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", board_mac=" + Arrays.toString(board_mac) +
                ", time_stamp=" + time_stamp +
                ", battery_temp=" + battery_temp +
                ", throttle_value=" + throttle_value +
                ", motor_current=" + motor_current +
                ", input_current=" + input_current +
                ", duty_cycle_now=" + duty_cycle_now +
                ", rpm=" + rpm +
                ", input_voltage=" + input_voltage +
                ", amp_hours=" + amp_hours +
                ", amp_hours_charged=" + amp_hours_charged +
                ", watt_hours=" + watt_hours +
                ", watt_hours_charged=" + watt_hours_charged +
                ", mosfet_temp=" + mosfet_temp +
                ", motor_temp=" + motor_temp +
                ", error_code=" + error_code +
                '}';
    }
}
