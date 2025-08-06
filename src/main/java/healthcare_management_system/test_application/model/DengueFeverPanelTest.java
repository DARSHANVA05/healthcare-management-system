package healthcare_management_system.test_application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dengueFunctionTest")
public class DengueFeverPanelTest {


    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int dftId;
    private int patientId;
    private String paymentStatus;

    private double dengueFeverAnitbodyIgGResult;
    private String dengueFeverAnitbodyIgGReferenceValue="<1.80";

    private double dengueFeverAnitbodyIgMResult;
    private String dengueFeverAnitbodyIgMReferenceValue = "<0.90";

    public String dateOfCompletion;

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String date;

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

    public int getDftId() {
        return dftId;
    }

    public void setDftId(int dftId) {
        this.dftId = dftId;
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

    public double getDengueFeverAnitbodyIgGResult() {
        return dengueFeverAnitbodyIgGResult;
    }

    public void setDengueFeverAnitbodyIgGResult(double dengueFeverAnitbodyIgGResult) {
        this.dengueFeverAnitbodyIgGResult = dengueFeverAnitbodyIgGResult;
    }

    public double getDengueFeverAnitbodyIgMResult() {
        return dengueFeverAnitbodyIgMResult;
    }

    public void setDengueFeverAnitbodyIgMResult(double dengueFeverAnitbodyIgMResult) {
        this.dengueFeverAnitbodyIgMResult = dengueFeverAnitbodyIgMResult;
    }

    public String getDengueFeverAnitbodyIgGReferenceValue() {
        return dengueFeverAnitbodyIgGReferenceValue;
    }

    public String getDengueFeverAnitbodyIgMReferenceValue() {
        return dengueFeverAnitbodyIgMReferenceValue;
    }
}