package healthcare_management_system.test_application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "prescription")
public class Prescription {


    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private @Getter
    @Setter int prescription;
    @Column(nullable = false)
    private @Getter @Setter int patientId;
    @Column(nullable = false)
    private @Getter @Setter int doctorId;
    @Column(nullable = false)
    private @Getter @Setter int appointmentId;
    @Column(nullable = false)
    private @Getter @Setter String notes;
    @Column(nullable = false)
    private @Getter @Setter String pulse;
    @Column(nullable = false)
    private @Getter @Setter String spo2;
    @Column(nullable = false)
    private @Getter @Setter String bp;
    @Column(nullable = false)
    private @Getter @Setter int weight;
    @Column(nullable = false)
    private @Getter @Setter String diagnosis;
    @Column(nullable = false)
    private @Getter @Setter String medication;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrescription() {
        return prescription;
    }

    public void setPrescription(int prescription) {
        this.prescription = prescription;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}
