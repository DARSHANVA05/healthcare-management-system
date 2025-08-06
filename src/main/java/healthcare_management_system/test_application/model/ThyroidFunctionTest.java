package healthcare_management_system.test_application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "thyroidFunctionTest")
public class ThyroidFunctionTest {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int tftId;
    private int patientId;
    private String paymentStatus;
    public double t3Result;
    public String t3ReferenceValue = "80.00-200.00";

    public double t4Result;
    public String t4ReferenceValue = "4.50-12.50";

    public double tshResult;
    public String tshReferenceValue = "0.40-4.00";

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

    public int getTftId() {
        return tftId;
    }

    public void setTftId(int tftId) {
        this.tftId = tftId;
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

    public double getT3Result() {
        return t3Result;
    }

    public void setT3Result(double t3Result) {
        this.t3Result = t3Result;
    }

    public double getT4Result() {
        return t4Result;
    }

    public void setT4Result(double t4Result) {
        this.t4Result = t4Result;
    }

    public double getTshResult() {
        return tshResult;
    }

    public void setTshResult(double tshResult) {
        this.tshResult = tshResult;
    }

    public String getT3ReferenceValue() {
        return t3ReferenceValue;
    }

    public String getT4ReferenceValue() {
        return t4ReferenceValue;
    }

    public String getTshReferenceValue() {
        return tshReferenceValue;
    }
}
