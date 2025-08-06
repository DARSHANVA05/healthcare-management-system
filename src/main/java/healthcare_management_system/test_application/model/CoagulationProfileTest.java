package healthcare_management_system.test_application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "coagulationProfileTest")
public class CoagulationProfileTest {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int cptId;

    private int patientId;
    private String paymentStatus;

    public int bleedingTimeResult;
    public String bleedingTimeReferenceValue = "3-10 min.";

    public int clottingTimeResult;
    public String clottingTimeReferenceValue = "2-7 min.";

    public double prothrombinTimeResult;
    public String prothrombinTimeReferenceValue = "10.3-12.8 sec.";

    public int activatedPartialThromboplastinTimeResult;
    public String activatedPartialThromboplastinTimeReferenceValue = "25-37 sec.";

    public String date;

    public String status;

    public String dateOfCompletion;

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String sampleCollection;
    public String getSampleCollection() {
        return sampleCollection;
    }
    public void setSampleCollection(String sampleCollection) {
        this.sampleCollection = sampleCollection;
    }

    public int getCptId() {
        return cptId;
    }

    public void setCptId(int cptId) {
        this.cptId = cptId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getBleedingTimeResult() {
        return bleedingTimeResult;
    }

    public void setBleedingTimeResult(int bleedingTimeResult) {
        this.bleedingTimeResult = bleedingTimeResult;
    }

    public int getClottingTimeResult() {
        return clottingTimeResult;
    }

    public void setClottingTimeResult(int clottingTimeResult) {
        this.clottingTimeResult = clottingTimeResult;
    }

    public double getProthrombinTimeResult() {
        return prothrombinTimeResult;
    }

    public void setProthrombinTimeResult(double prothrombinTimeResult) {
        this.prothrombinTimeResult = prothrombinTimeResult;
    }

    public int getActivatedPartialThromboplastinTimeResult() {
        return activatedPartialThromboplastinTimeResult;
    }

    public void setActivatedPartialThromboplastinTimeResult(int activatedPartialThromboplastinTimeResult) {
        this.activatedPartialThromboplastinTimeResult = activatedPartialThromboplastinTimeResult;
    }

    public String getBleedingTimeReferenceValue() {
        return bleedingTimeReferenceValue;
    }

    public String getClottingTimeReferenceValue() {
        return clottingTimeReferenceValue;
    }

    public String getProthrombinTimeReferenceValue() {
        return prothrombinTimeReferenceValue;
    }

    public String getActivatedPartialThromboplastinTimeReferenceValue() {
        return activatedPartialThromboplastinTimeReferenceValue;
    }
}
