package healthcare_management_system.test_application.service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import healthcare_management_system.test_application.dto.LabTestsDto;
import healthcare_management_system.test_application.model.*;
import healthcare_management_system.test_application.repository.*;
import healthcare_management_system.test_application.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

import static com.lowagie.text.FontFactory.HELVETICA_BOLD;

@Service
public class LaboratoryService {

    @Autowired
    CptRepository cptRepository;
    @Autowired
    CbctRepository cbctRepository;
    @Autowired
    DfptRepository dfptRepository;
    @Autowired
    KftRepository kftRepository;
    @Autowired
    LptRepository lptRepository;
    @Autowired
    LftRepository lftRepository;
    @Autowired
    TftRepository tftRepository;
    @Autowired
    TechnicianRepository technicianRepository;

    public void saveCpt(CoagulationProfileTest coagulationProfileTest) {
            this.cptRepository.save(coagulationProfileTest);
    }
    public void saveCbct(CompleteBloodCountTest completeBloodCountTest) {
            cbctRepository.save(completeBloodCountTest);
    }
    public void saveDfpt(DengueFeverPanelTest dengueFeverPanelTest) {dfptRepository.save(dengueFeverPanelTest);}
    public void saveKft(KidneyFunctionTest kidneyFunctionTest) {
            kftRepository.save(kidneyFunctionTest);
    }
    public void saveLpt(LipidProfileTest lipidProfileTest) {
            lptRepository.save(lipidProfileTest);
    }
    public void saveLft(LiverFunctionTest liverFunctionTest) {
            lftRepository.save(liverFunctionTest);
    }
    public void saveTft(ThyroidFunctionTest thyroidFunctionTest) {
            tftRepository.save(thyroidFunctionTest);
    }

    public LabTechnician password(int userInp) {
        Optional<LabTechnician> optional = technicianRepository.findById(userInp);
        LabTechnician labTechnician = null;
        if (optional.isPresent()) {
            labTechnician = optional.get();
        } else {
            throw new RuntimeException(" Lab technician not found for id");
        }
        return labTechnician;
    }
    //Coagulation profile test
    public CoagulationProfileTest findCptById(int cptId) {
        Optional<CoagulationProfileTest> optional = cptRepository.findById(cptId);
        CoagulationProfileTest coagulationProfileTest = null;
        if (optional.isPresent()) {
            coagulationProfileTest = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return coagulationProfileTest;
    }

    public ByteArrayInputStream downloadCpt(CoagulationProfileTest coagulationProfileTest, Patients patients) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,outputStream);
        document.open();
        Font font = Font.getFont(HELVETICA_BOLD);
        Paragraph title = new Paragraph("CommunityCare Diagnostics\n\n");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph patientDetails = new Paragraph("Test name: Complete blood count test\n"+
                "Patient name: "+patients.getFirstName()+" "+patients.getLastName()+"\n"+
                "Age: "+patients.getAge()+" years\n" +
                "Date of completion: "+coagulationProfileTest.getDateOfCompletion()+
                "\n\nBleeding Time (BT): "+coagulationProfileTest.getBleedingTimeResult()+
                "\nBleeding Time (BT) Reference Value: "+coagulationProfileTest.getBleedingTimeReferenceValue()+
                "\n\nClotting Time (CT): "+coagulationProfileTest.getClottingTimeResult()+
                "\nClotting Time (CT) Reference Value: "+coagulationProfileTest.getClottingTimeReferenceValue()+
                "\n\nProthrombin Time (PT): "+coagulationProfileTest.getProthrombinTimeResult()+
                "\nProthrombin Time (PT) Reference Value: "+coagulationProfileTest.getProthrombinTimeReferenceValue()+
                "\n\nActivated Partial Thromboplastin Time: "+coagulationProfileTest.getActivatedPartialThromboplastinTimeResult()+
                "\nActivated Partial Thromboplastin Time Reference Value: "+coagulationProfileTest.getActivatedPartialThromboplastinTimeReferenceValue()
        );

        patientDetails.setAlignment(Element.ALIGN_LEFT);
        document.add(patientDetails);
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());

    }

    //Complete blood count test
    public CompleteBloodCountTest findCbctById(int cbcId) {
        Optional<CompleteBloodCountTest> optional = cbctRepository.findById(cbcId);
        CompleteBloodCountTest completeBloodCountTest = null;
        if (optional.isPresent()) {
            completeBloodCountTest = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return completeBloodCountTest;
    }
    public ByteArrayInputStream downloadCbct(CompleteBloodCountTest completeBloodCountTest, Patients patients) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,outputStream);
        document.open();
        Font font = Font.getFont(HELVETICA_BOLD);
        Paragraph title = new Paragraph("CommunityCare Diagnostics\n\n");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
                
        Paragraph patientDetails = new Paragraph("Test name: Complete blood count test\n\n"+
                "Patient name: "+patients.getFirstName()+" "+patients.getLastName()+"\n"+
                "Age: "+patients.getAge()+" years\n" +
                "Date of completion: "+completeBloodCountTest.getDateOfCompletion()+
                "\n\nHemoglobin: "+completeBloodCountTest.getHemoglobinResult()+
                "\nHemoglobin Reference Value: "+completeBloodCountTest.getHemoglobinReferenceValue()+
                "\n\nRBC Count: "+completeBloodCountTest.getRbcCountResult()+
                "\nRBC Count Reference Value: "+completeBloodCountTest.getRbcCountReferenceValue()+
                "\n\nBlood indices: "+completeBloodCountTest.getBloodIndicesResult()+
                "\nBlood indices Reference Value: "+completeBloodCountTest.getBloodIndicesReferenceValue()+
                "\n\nMean Corpuscular Volume (MCV): "+completeBloodCountTest.getMcvResult()+
                "\nMean Corpuscular Volume (MCV) Reference Value: "+completeBloodCountTest.getMcvReferenceValue()+
                "\n\nMCH: "+completeBloodCountTest.getMchResult()+
                "\nMCH Reference Value: "+completeBloodCountTest.getMchReferenceValue()+
                "\n\nMCHC: "+completeBloodCountTest.getMchcResult()+
                "\nMCHC Reference Value: "+completeBloodCountTest.getMchcReferenceValue()+
                "\n\nRDW: "+completeBloodCountTest.getRdwResult()+
                "\nRDW Reference Value: "+completeBloodCountTest.getRdwReferenceValue()+
                "\n\nTotal WBC Count: "+completeBloodCountTest.getTotalWbcCountResult()+
                "\nTotal WBC Count Reference Value: "+completeBloodCountTest.getTotalWbcCountReferenceValue()+
                "\n\nNeutrophils: "+completeBloodCountTest.getNeutrophilsResult()+
                "\nNeutrophils Reference Value: "+completeBloodCountTest.getNeutrophilsReferenceValue()+
                "\n\nLymphocytes: "+completeBloodCountTest.getLymphocytesResult()+
                "\nLymphocytes Reference Value: "+completeBloodCountTest.getLymphocytesReferenceValue()+
                "\n\nEosinophils: "+completeBloodCountTest.getEosinophilsResult()+
                "\nEosinophils Reference Value: "+completeBloodCountTest.getEosinophilsReferenceValue()+
                "\n\n\nMonocytes: "+completeBloodCountTest.getMonocytesResult()+
                "\nMonocytes Reference Value: "+completeBloodCountTest.getMonocytesReferenceValue()+
                "\n\nBasophils: "+completeBloodCountTest.getBasophilsResult()+
                "\nBasophils Reference Value: "+completeBloodCountTest.getBasophilsReferenceValue()+
                "\n\nPlatelet Count: "+completeBloodCountTest.getPlateletCountResult()+
                "\nPlatelet Count Reference Value: "+completeBloodCountTest.getPlateletCountReferenceValue()
        );

        patientDetails.setAlignment(Element.ALIGN_LEFT);
        document.add(patientDetails);
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());

    }


    //Dengue fever panel test
    public DengueFeverPanelTest findDfptById(int dfptId) {
        Optional<DengueFeverPanelTest> optional = dfptRepository.findById(dfptId);
        DengueFeverPanelTest dengueFeverPanelTest = null;
        if (optional.isPresent()) {
            dengueFeverPanelTest = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return dengueFeverPanelTest;
    }
    public ByteArrayInputStream downloadDfpt(DengueFeverPanelTest dengueFeverPanelTest, Patients patients) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,outputStream);
        document.open();
        Font font = Font.getFont(HELVETICA_BOLD);
        Paragraph title = new Paragraph("CommunityCare Diagnostics\n\n");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph patientDetails = new Paragraph("Test name: Dengue fever panel test\n\n"+
                "Patient name: "+patients.getFirstName()+" "+patients.getLastName()+"\n"+
                "Age: "+patients.getAge()+" years\n" +
                "Date of completion: "+dengueFeverPanelTest.getDateOfCompletion()+
                "\n\nDengue Fever Antibody, IgG, Serum: "+dengueFeverPanelTest.getDengueFeverAnitbodyIgGResult()+
                "\nDengue Fever Antibody, IgG, Serum Reference Value: "+dengueFeverPanelTest.getDengueFeverAnitbodyIgGReferenceValue()+
                "\n\nDengue Fever Antibody, IgM, Serum: "+dengueFeverPanelTest.getDengueFeverAnitbodyIgMResult()+
                "\nDengue Fever Antibody, IgM, Serum Reference Value: "+dengueFeverPanelTest.getDengueFeverAnitbodyIgMReferenceValue()
        );

        patientDetails.setAlignment(Element.ALIGN_LEFT);
        document.add(patientDetails);
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());

    }
    //Kidney function test
    public KidneyFunctionTest findKftById(int kftId) {
        Optional<KidneyFunctionTest> optional = kftRepository.findById(kftId);
        KidneyFunctionTest kidneyFunctionTest = null;
        if (optional.isPresent()) {
            kidneyFunctionTest = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return kidneyFunctionTest;
    }
    public ByteArrayInputStream downloadKft(KidneyFunctionTest kidneyFunctionTest, Patients patients) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,outputStream);
        document.open();
        Font font = Font.getFont(HELVETICA_BOLD);
        Paragraph title = new Paragraph("CommunityCare Diagnostics\n\n");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph patientDetails = new Paragraph("Test name: Kidney function test\n\n"+
                "Patient name: "+patients.getFirstName()+" "+patients.getLastName()+"\n"+
                "Age: "+patients.getAge()+" years\n" +
                "Date of completion: "+kidneyFunctionTest.getDateOfCompletion()+
                "\n\nUrea: "+kidneyFunctionTest.getUreaResult()+
                "\nUrea Reference Value: "+kidneyFunctionTest.getUreaReferenceValue()+
                "\n\nCreatinine: "+kidneyFunctionTest.getCreatinineResult()+
                "\nCreatinine Reference Value: "+kidneyFunctionTest.getCreatinineReferenceValue()+
                "\n\nUric Acid: "+kidneyFunctionTest.getUricAcidResult()+
                "\nUric Acid Reference Value: "+kidneyFunctionTest.getUricAcidReferenceValue()+
                "\n\nCalcium, Total: "+kidneyFunctionTest.getCalciumResult()+
                "\nCalcium, Total Reference Value: "+kidneyFunctionTest.getCalciumReferenceValue()+
                "\n\nPhosphorus: "+kidneyFunctionTest.getPhosphorusResult()+
                "\nPhosphorus Reference Value: "+kidneyFunctionTest.getPhosphorusReferenceValue()+
                "\n\nAlkaline Phosphatase (ALP): "+kidneyFunctionTest.getAlkalinePhosphataseResult()+
                "\nAlkaline Phosphatase (ALP) Reference Value: "+kidneyFunctionTest.getAlkalinePhosphataseReferenceValue()+
                "\n\nTotal Protein: "+kidneyFunctionTest.getTotalProteinResult()+
                "\nTotal Protein Reference Value: "+kidneyFunctionTest.getTotalProteinReferenceValue()+
                "\n\nAlbumin: "+kidneyFunctionTest.getAlbuminResult()+
                "\nAlbumin Reference Value: "+kidneyFunctionTest.getAlbuminReferenceValue()+
                "\n\nSodium: "+kidneyFunctionTest.getSodiumResult()+
                "\nSodium Reference Value: "+kidneyFunctionTest.getSodiumReferenceValue()+
                "\n\nPotassium: "+kidneyFunctionTest.getPotassiumResult()+
                "\nPotassium Value: "+kidneyFunctionTest.getPotassiumReferenceValue()+
                "\n\nChloride: "+kidneyFunctionTest.getChlorideResult()+
                "\nChloride Value: "+kidneyFunctionTest.getChlorideReferenceValue()
        );

        patientDetails.setAlignment(Element.ALIGN_LEFT);
        document.add(patientDetails);
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());

    }

    //Lipid profile test
    public LipidProfileTest findLptById(int lptId) {
        Optional<LipidProfileTest> optional = lptRepository.findById(lptId);
        LipidProfileTest lipidProfileTest = null;
        if (optional.isPresent()) {
            lipidProfileTest = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return lipidProfileTest;
    }
    public ByteArrayInputStream downloadLpt(LipidProfileTest lipidProfileTest,
                                            Patients patients) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,outputStream);
        document.open();
        Font font = Font.getFont(HELVETICA_BOLD);
        Paragraph title = new Paragraph("CommunityCare Diagnostics\n\n");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph patientDetails = new Paragraph("Test name: Lipid profile test\n\n"+
                "Patient name: "+patients.getFirstName()+" "+patients.getLastName()+"\n"+
                "Age: "+patients.getAge()+" years\n" +
                "Date of completion: "+lipidProfileTest.getDateOfCompletion()+
                "\n\nCholesterol Total: "+lipidProfileTest.getCholesterolTotalResult()+
                "\nCholesterol Total Reference Value: "+lipidProfileTest.getCholesterolTotalReferenceValue()+
                "\n\nTriglycerides: "+lipidProfileTest.getTriglyceridesResult()+
                "\nTriglycerides Reference Value: "+lipidProfileTest.getTriglyceridesReferenceValue()+
                "\n\nHDL Cholesterol: "+lipidProfileTest.getHdlCholesterolResult()+
                "\nHDL Cholesterol Reference Value: "+lipidProfileTest.getHdlCholesterolReferenceValue()+
                "\n\nLDL Cholesterol: "+lipidProfileTest.getLdlCholesterolResult()+
                "\nLDL Cholesterol Reference Value: "+lipidProfileTest.getLdlCholesterolReferenceValue()+
                "\n\nVLDL Cholesterol: "+lipidProfileTest.getVldlCholesterolResult()+
                "\nVLDL Cholesterol Reference Value: "+lipidProfileTest.getVldlCholesterolReferenceValue());

        patientDetails.setAlignment(Element.ALIGN_LEFT);
        document.add(patientDetails);
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    //Liver function test
    public LiverFunctionTest findLftById(int lftId) {
        Optional<LiverFunctionTest> optional = lftRepository.findById(lftId);
        LiverFunctionTest liverFunctionTest = null;
        if (optional.isPresent()) {
            liverFunctionTest = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return liverFunctionTest;
    }
    public ByteArrayInputStream downloadLft(LiverFunctionTest liverFunctionTest,
                                            Patients patients) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,outputStream);
        document.open();
        Font font = Font.getFont(HELVETICA_BOLD);
        Paragraph title = new Paragraph("CommunityCare Diagnostics\n\n");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph patientDetails = new Paragraph("Test name: Liver function test\n\n"+
                "Patient name: "+patients.getFirstName()+" "+patients.getLastName()+"\n"+
                "Age: "+patients.getAge()+" years\n" +
                "Date of completion: "+liverFunctionTest.getDateOfCompletion()+
                "\n\nAST (SGOT): "+liverFunctionTest.getAstResult()+
                "\nAST (SGOT) Reference Value: "+liverFunctionTest.getAstReferenceValue()+
                "\n\nALT (SGPT): "+liverFunctionTest.getAltResult()+
                "\nALT (SGPT) Reference Value: "+liverFunctionTest.getAltReferenceValue()+
                "\n\nAST:ALT Ratio: "+liverFunctionTest.getAstAltRatioResult()+
                "\nAST:ALT Ratio Reference Value: "+liverFunctionTest.getAstAltRatioReferenceValue()+
                "\n\nGGTP: "+liverFunctionTest.getGgtpResult()+
                "\nGGTP Ratio Reference Value: "+liverFunctionTest.getGgtpReferenceValue()+
                "\n\nAlkaline Phosphatase (ALP): "+liverFunctionTest.getAlkalinePhosphataseResult()+
                "\nAlkaline Phosphatase (ALP) Reference Value: "+liverFunctionTest.getAlkalinePhosphataseReferenceValue()+
                "\n\nBilirubin Total: "+liverFunctionTest.getBilirubinTotalResult()+
                "\nBilirubin Total Reference Value: "+liverFunctionTest.getBilirubinTotalReferenceValue()+
                "\n\nBilirubin Direct: "+liverFunctionTest.getBilirubinDirectResult()+
                "\nBilirubin Direct Reference Value: "+liverFunctionTest.getBilirubinDirectReferenceValue()+
                "\n\nBilirubin Indirect: "+liverFunctionTest.getBilirubinIndirectResult()+
                "\nBilirubin Indirect Reference Value: "+liverFunctionTest.getBilirubinIndirectReferenceValue()+
                "\n\nTotal Protein: "+liverFunctionTest.getTotalProteinResult()+
                "\nTotal Protein Reference Value: "+liverFunctionTest.getTotalProteinReferenceValue()+
                "\n\nAlbumin: "+liverFunctionTest.getAlbuminResult()+
                "\nAlbumin Reference Value: "+liverFunctionTest.getAlbuminReferenceValue()+
                "\n\nA:G Ratio: "+liverFunctionTest.getAgRatioResult()+
                "\nA:G RatioReference Value: "+liverFunctionTest.getAgRatioReferenceValue());

        patientDetails.setAlignment(Element.ALIGN_LEFT);
        document.add(patientDetails);
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    //Thyroid function test
    public ThyroidFunctionTest findTftById(int tftId) {
        Optional<ThyroidFunctionTest> optional = tftRepository.findById(tftId);
        ThyroidFunctionTest thyroidFunctionTest = null;
        if (optional.isPresent()) {
            thyroidFunctionTest = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return thyroidFunctionTest;
    }
    public ByteArrayInputStream downloadTft(ThyroidFunctionTest thyroidFunctionTest,
                                            Patients patients) {


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,outputStream);
        document.open();
        Font font = Font.getFont(HELVETICA_BOLD);
        Paragraph title = new Paragraph("CommunityCare Diagnostics\n\n");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph patientDetails = new Paragraph("Test name: Thyroid function test\n\n"+
                "Patient name: "+patients.getFirstName()+" "+patients.getLastName()+"\n"+
                "Age: "+patients.getAge()+" years\n" +
                "Date of completion: "+thyroidFunctionTest.getDateOfCompletion()+
                "\n\nT3, Total, Serum: "+thyroidFunctionTest.getT3Result()+"        Reference Value: "+thyroidFunctionTest.getT3ReferenceValue()+"\n" +
                "T4, Total, Serum: "+thyroidFunctionTest.getT4Result()+"        Reference Value: "+thyroidFunctionTest.getT4ReferenceValue()+"\n" +
                "TSH : "+thyroidFunctionTest.getTshResult()+"                           Reference Value: "+thyroidFunctionTest.getTshReferenceValue()+"\n" );
        patientDetails.setAlignment(Element.ALIGN_LEFT);
        document.add(patientDetails);
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }



}
