package healthcare_management_system.test_application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "completeBloodCountTest")
public class CompleteBloodCountTest {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int cbcId;
    private int patientId;
    private String paymentStatus;
    public Double hemoglobinResult;
    public String hemoglobinReferenceValue = "13.0-17.0";

    public Double rbcCountResult;
    public String rbcCountReferenceValue = "4.5-5.5";

    public Double bloodIndicesResult;
    public String bloodIndicesReferenceValue = "40-50";

    public Double mcvResult; // Mean Corpuscular Volume
    public String mcvReferenceValue = "83-101";

    public Double mchResult; // Mean Corpuscular Hemoglobin
    public String mchReferenceValue = "27-32";

    public Double mchcResult; // Mean Corpuscular Hemoglobin Concentration
    public String mchcReferenceValue = "32.5-34.5";

    public Double rdwResult; // Red Cell Distribution Width
    public String rdwReferenceValue = "11.6-14.0";

    public Integer totalWbcCountResult;
    public String totalWbcCountReferenceValue = "4000-11000";

    public Integer neutrophilsResult;
    public String neutrophilsReferenceValue = "50-62";

    public Integer lymphocytesResult;
    public String lymphocytesReferenceValue = "20-40";

    public Integer eosinophilsResult;
    public String eosinophilsReferenceValue = "00-06";

    public Integer monocytesResult;
    public String monocytesReferenceValue = "00-10";

    public Integer basophilsResult;
    public String basophilsReferenceValue = "00-02";

    public Integer plateletCountResult;
    public String plateletCountReferenceValue = "150000-410000";

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

    public int getCbcId() {
        return cbcId;
    }

    public void setCbcId(int cbcId) {
        this.cbcId = cbcId;
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

    public Double getHemoglobinResult() {
        return hemoglobinResult;
    }

    public void setHemoglobinResult(Double hemoglobinResult) {
        this.hemoglobinResult = hemoglobinResult;
    }

    public Double getRbcCountResult() {
        return rbcCountResult;
    }

    public void setRbcCountResult(Double rbcCountResult) {
        this.rbcCountResult = rbcCountResult;
    }

    public Double getBloodIndicesResult() {
        return bloodIndicesResult;
    }

    public void setBloodIndicesResult(Double bloodIndicesResult) {
        this.bloodIndicesResult = bloodIndicesResult;
    }

    public Double getMcvResult() {
        return mcvResult;
    }

    public void setMcvResult(Double mcvResult) {
        this.mcvResult = mcvResult;
    }

    public Double getMchResult() {
        return mchResult;
    }

    public void setMchResult(Double mchResult) {
        this.mchResult = mchResult;
    }

    public Double getMchcResult() {
        return mchcResult;
    }

    public void setMchcResult(Double mchcResult) {
        this.mchcResult = mchcResult;
    }

    public Double getRdwResult() {
        return rdwResult;
    }

    public void setRdwResult(Double rdwResult) {
        this.rdwResult = rdwResult;
    }

    public Integer getTotalWbcCountResult() {
        return totalWbcCountResult;
    }

    public void setTotalWbcCountResult(Integer totalWbcCountResult) {
        this.totalWbcCountResult = totalWbcCountResult;
    }

    public Integer getNeutrophilsResult() {
        return neutrophilsResult;
    }

    public void setNeutrophilsResult(Integer neutrophilsResult) {
        this.neutrophilsResult = neutrophilsResult;
    }

    public Integer getLymphocytesResult() {
        return lymphocytesResult;
    }

    public void setLymphocytesResult(Integer lymphocytesResult) {
        this.lymphocytesResult = lymphocytesResult;
    }

    public Integer getEosinophilsResult() {
        return eosinophilsResult;
    }

    public void setEosinophilsResult(Integer eosinophilsResult) {
        this.eosinophilsResult = eosinophilsResult;
    }

    public Integer getMonocytesResult() {
        return monocytesResult;
    }

    public void setMonocytesResult(Integer monocytesResult) {
        this.monocytesResult = monocytesResult;
    }

    public Integer getBasophilsResult() {
        return basophilsResult;
    }

    public void setBasophilsResult(Integer basophilsResult) {
        this.basophilsResult = basophilsResult;
    }

    public Integer getPlateletCountResult() {
        return plateletCountResult;
    }

    public void setPlateletCountResult(Integer plateletCountResult) {
        this.plateletCountResult = plateletCountResult;
    }

    public String getHemoglobinReferenceValue() {
        return hemoglobinReferenceValue;
    }

    public String getRbcCountReferenceValue() {
        return rbcCountReferenceValue;
    }

    public String getBloodIndicesReferenceValue() {
        return bloodIndicesReferenceValue;
    }

    public String getMcvReferenceValue() {
        return mcvReferenceValue;
    }

    public String getMchReferenceValue() {
        return mchReferenceValue;
    }

    public String getMchcReferenceValue() {
        return mchcReferenceValue;
    }

    public String getRdwReferenceValue() {
        return rdwReferenceValue;
    }

    public String getTotalWbcCountReferenceValue() {
        return totalWbcCountReferenceValue;
    }

    public String getNeutrophilsReferenceValue() {
        return neutrophilsReferenceValue;
    }

    public String getLymphocytesReferenceValue() {
        return lymphocytesReferenceValue;
    }

    public String getEosinophilsReferenceValue() {
        return eosinophilsReferenceValue;
    }

    public String getMonocytesReferenceValue() {
        return monocytesReferenceValue;
    }

    public String getBasophilsReferenceValue() {
        return basophilsReferenceValue;
    }

    public String getPlateletCountReferenceValue() {
        return plateletCountReferenceValue;
    }
}
