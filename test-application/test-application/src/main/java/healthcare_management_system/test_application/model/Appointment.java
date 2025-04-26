package healthcare_management_system.test_application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int appointmentId;
    @Column(nullable = false)
    private @Getter @Setter int patientId;
    @Column(nullable = false)
    private @Getter @Setter int doctorId;
    @Column(nullable = false)
    private @Getter @Setter String date;
    @Column(nullable = false)
    private @Getter @Setter String time;
    @Column(nullable = false)
    private @Getter @Setter String paymentType;
    @Column(nullable = false)
    private @Getter @Setter int amount;
    @Column(nullable = false)
    private @Getter @Setter String paymentStatus ;
    @Column(nullable = false)
    private @Getter @Setter String description ;

    @Column(nullable = false)
    private @Getter @Setter String consulted ;

    @Column
    private String attendance;

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getConsulted() {
        return consulted;
    }

    public void setConsulted(String consulted) {
        this.consulted = consulted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
