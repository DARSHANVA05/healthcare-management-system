package healthcare_management_system.test_application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lipidProfileTest")
public class LipidProfileTest{

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int lptId;
    private int patientId;
    private String paymentStatus;

    public double cholesterolTotalResult;
    public String cholesterolTotalReferenceValue = "<200.00";

    public double triglyceridesResult;
    public String triglyceridesReferenceValue = "<150.00";

    public double hdlCholesterolResult;
    public String hdlCholesterolReferenceValue = ">40.00";

    public double ldlCholesterolResult;
    public String ldlCholesterolReferenceValue = "<100.00";

    public double vldlCholesterolResult;
    public String vldlCholesterolReferenceValue = "<30.00";

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

    public int getLptId() {
        return lptId;
    }

    public void setLptId(int lptId) {
        this.lptId = lptId;
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

    public double getCholesterolTotalResult() {
        return cholesterolTotalResult;
    }

    public void setCholesterolTotalResult(double cholesterolTotalResult) {
        this.cholesterolTotalResult = cholesterolTotalResult;
    }

    public double getTriglyceridesResult() {
        return triglyceridesResult;
    }

    public void setTriglyceridesResult(double triglyceridesResult) {
        this.triglyceridesResult = triglyceridesResult;
    }

    public double getHdlCholesterolResult() {
        return hdlCholesterolResult;
    }

    public void setHdlCholesterolResult(double hdlCholesterolResult) {
        this.hdlCholesterolResult = hdlCholesterolResult;
    }

    public double getLdlCholesterolResult() {
        return ldlCholesterolResult;
    }

    public void setLdlCholesterolResult(double ldlCholesterolResult) {
        this.ldlCholesterolResult = ldlCholesterolResult;
    }

    public double getVldlCholesterolResult() {
        return vldlCholesterolResult;
    }

    public void setVldlCholesterolResult(double vldlCholesterolResult) {
        this.vldlCholesterolResult = vldlCholesterolResult;
    }

    public String getCholesterolTotalReferenceValue() {
        return cholesterolTotalReferenceValue;
    }

    public String getTriglyceridesReferenceValue() {
        return triglyceridesReferenceValue;
    }

    public String getHdlCholesterolReferenceValue() {
        return hdlCholesterolReferenceValue;
    }

    public String getLdlCholesterolReferenceValue() {
        return ldlCholesterolReferenceValue;
    }

    public String getVldlCholesterolReferenceValue() {
        return vldlCholesterolReferenceValue;
    }
}
