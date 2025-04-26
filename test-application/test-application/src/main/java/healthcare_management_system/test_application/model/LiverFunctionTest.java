package healthcare_management_system.test_application.model;

import jakarta.persistence.*;

@Entity
@Table(name = "liverFunctionTest")
public class LiverFunctionTest {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int lftId;
    private int patientId;
    private String paymentStatus;
    public Double astResult; // SGOT
    public String astReferenceValue = "15.00-40.00";

    public Double altResult; // SGPT
    public String altReferenceValue = "10.00-49.00";

    public Double astAltRatioResult; // AST:ALT Ratio
    public String astAltRatioReferenceValue = "<1.00";

    public Double ggtpResult; // GGTP
    public String ggtpReferenceValue = "0-73";

    public Double alkalinePhosphataseResult; // ALP
    public String alkalinePhosphataseReferenceValue = "30.00-120.00";

    public Double bilirubinTotalResult;
    public String bilirubinTotalReferenceValue = "0.30-1.20";

    public Double bilirubinDirectResult;
    public String bilirubinDirectReferenceValue = "<0.3";

    public Double bilirubinIndirectResult;
    public String bilirubinIndirectReferenceValue = "<1.10";

    public Double totalProteinResult;
    public String totalProteinReferenceValue = "5.70-8.20";

    public Double albuminResult;
    public String albuminReferenceValue = "3.20-4.80";

    public Double agRatioResult; // A:G Ratio
    public String agRatioReferenceValue = "0.90-2.00";

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

    public int getLftId() {
        return lftId;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public Double getAstResult() {
        return astResult;
    }

    public String getAstReferenceValue() {
        return astReferenceValue;
    }

    public Double getAltResult() {
        return altResult;
    }

    public String getAltReferenceValue() {
        return altReferenceValue;
    }

    public Double getAstAltRatioResult() {
        return astAltRatioResult;
    }

    public String getAstAltRatioReferenceValue() {
        return astAltRatioReferenceValue;
    }

    public Double getGgtpResult() {
        return ggtpResult;
    }

    public String getGgtpReferenceValue() {
        return ggtpReferenceValue;
    }

    public Double getAlkalinePhosphataseResult() {
        return alkalinePhosphataseResult;
    }

    public String getAlkalinePhosphataseReferenceValue() {
        return alkalinePhosphataseReferenceValue;
    }

    public Double getBilirubinTotalResult() {
        return bilirubinTotalResult;
    }

    public String getBilirubinTotalReferenceValue() {
        return bilirubinTotalReferenceValue;
    }

    public Double getBilirubinDirectResult() {
        return bilirubinDirectResult;
    }

    public String getBilirubinDirectReferenceValue() {
        return bilirubinDirectReferenceValue;
    }

    public Double getBilirubinIndirectResult() {
        return bilirubinIndirectResult;
    }

    public String getBilirubinIndirectReferenceValue() {
        return bilirubinIndirectReferenceValue;
    }

    public Double getTotalProteinResult() {
        return totalProteinResult;
    }

    public String getTotalProteinReferenceValue() {
        return totalProteinReferenceValue;
    }

    public Double getAlbuminResult() {
        return albuminResult;
    }

    public String getAlbuminReferenceValue() {
        return albuminReferenceValue;
    }

    public Double getAgRatioResult() {
        return agRatioResult;
    }

    public String getAgRatioReferenceValue() {
        return agRatioReferenceValue;
    }

    public void setLftId(int lftId) {
        this.lftId = lftId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setAstResult(Double astResult) {
        this.astResult = astResult;
    }

    public void setAltResult(Double altResult) {
        this.altResult = altResult;
    }

    public void setAstAltRatioResult(Double astAltRatioResult) {
        this.astAltRatioResult = astAltRatioResult;
    }

    public void setGgtpResult(Double ggtpResult) {
        this.ggtpResult = ggtpResult;
    }

    public void setAlkalinePhosphataseResult(Double alkalinePhosphataseResult) {
        this.alkalinePhosphataseResult = alkalinePhosphataseResult;
    }

    public void setBilirubinTotalResult(Double bilirubinTotalResult) {
        this.bilirubinTotalResult = bilirubinTotalResult;
    }

    public void setBilirubinDirectResult(Double bilirubinDirectResult) {
        this.bilirubinDirectResult = bilirubinDirectResult;
    }

    public void setBilirubinIndirectResult(Double bilirubinIndirectResult) {
        this.bilirubinIndirectResult = bilirubinIndirectResult;
    }

    public void setTotalProteinResult(Double totalProteinResult) {
        this.totalProteinResult = totalProteinResult;
    }

    public void setAlbuminResult(Double albuminResult) {
        this.albuminResult = albuminResult;
    }

    public void setAgRatioResult(Double agRatioResult) {
        this.agRatioResult = agRatioResult;
    }
}
