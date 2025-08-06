package healthcare_management_system.test_application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kidneyFunctionTest")
public class KidneyFunctionTest {


    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int kftId;
    private int patientId;
    private String paymentStatus;
    private double ureaResult;
    private String ureaReferenceValue = "13-43";

    private double creatinineResult;
    private String creatinineReferenceValue = "0.7-1.3";

    private double uricAcidResult;
    private String uricAcidReferenceValue = "3.5-7.2";

    private double calciumResult;
    private String calciumReferenceValue = "8.7-10.4";

    private double phosphorusResult;
    private String phosphorusReferenceValue = "2.4-5.1";

    private double alkalinePhosphataseResult;
    private String alkalinePhosphataseReferenceValue = "30-120";

    private double totalProteinResult;
    private String totalProteinReferenceValue = "5.7-8.2";

    private double albuminResult;
    private String albuminReferenceValue = "3.2-4.8";

    private double sodiumResult;
    private String sodiumReferenceValue = "136-145";

    private double potassiumResult;
    private String potassiumReferenceValue = "3.5-5.1";

    private double chlorideResult;
    private String chlorideReferenceValue = "98-107";

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

    public int getKftId() {
        return kftId;
    }

    public void setKftId(int kftId) {
        this.kftId = kftId;
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

    public double getUreaResult() {
        return ureaResult;
    }

    public void setUreaResult(double ureaResult) {
        this.ureaResult = ureaResult;
    }

    public double getCreatinineResult() {
        return creatinineResult;
    }

    public void setCreatinineResult(double creatinineResult) {
        this.creatinineResult = creatinineResult;
    }

    public double getUricAcidResult() {
        return uricAcidResult;
    }

    public void setUricAcidResult(double uricAcidResult) {
        this.uricAcidResult = uricAcidResult;
    }

    public double getCalciumResult() {
        return calciumResult;
    }

    public void setCalciumResult(double calciumResult) {
        this.calciumResult = calciumResult;
    }

    public double getPhosphorusResult() {
        return phosphorusResult;
    }

    public void setPhosphorusResult(double phosphorusResult) {
        this.phosphorusResult = phosphorusResult;
    }

    public double getAlkalinePhosphataseResult() {
        return alkalinePhosphataseResult;
    }

    public void setAlkalinePhosphataseResult(double alkalinePhosphataseResult) {
        this.alkalinePhosphataseResult = alkalinePhosphataseResult;
    }

    public double getTotalProteinResult() {
        return totalProteinResult;
    }

    public void setTotalProteinResult(double totalProteinResult) {
        this.totalProteinResult = totalProteinResult;
    }

    public double getAlbuminResult() {
        return albuminResult;
    }

    public void setAlbuminResult(double albuminResult) {
        this.albuminResult = albuminResult;
    }

    public double getSodiumResult() {
        return sodiumResult;
    }

    public void setSodiumResult(double sodiumResult) {
        this.sodiumResult = sodiumResult;
    }

    public double getPotassiumResult() {
        return potassiumResult;
    }

    public void setPotassiumResult(double potassiumResult) {
        this.potassiumResult = potassiumResult;
    }

    public double getChlorideResult() {
        return chlorideResult;
    }

    public void setChlorideResult(double chlorideResult) {
        this.chlorideResult = chlorideResult;
    }

    public String getUreaReferenceValue() {
        return ureaReferenceValue;
    }

    public String getCreatinineReferenceValue() {
        return creatinineReferenceValue;
    }

    public String getUricAcidReferenceValue() {
        return uricAcidReferenceValue;
    }

    public String getCalciumReferenceValue() {
        return calciumReferenceValue;
    }

    public String getPhosphorusReferenceValue() {
        return phosphorusReferenceValue;
    }

    public String getAlkalinePhosphataseReferenceValue() {
        return alkalinePhosphataseReferenceValue;
    }

    public String getTotalProteinReferenceValue() {
        return totalProteinReferenceValue;
    }

    public String getAlbuminReferenceValue() {
        return albuminReferenceValue;
    }

    public String getSodiumReferenceValue() {
        return sodiumReferenceValue;
    }

    public String getPotassiumReferenceValue() {
        return potassiumReferenceValue;
    }

    public String getChlorideReferenceValue() {
        return chlorideReferenceValue;
    }
}
