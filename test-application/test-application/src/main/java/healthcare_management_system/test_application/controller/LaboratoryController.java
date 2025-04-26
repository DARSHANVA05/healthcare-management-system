package healthcare_management_system.test_application.controller;

import healthcare_management_system.test_application.dto.LabTestsDto;
import healthcare_management_system.test_application.model.*;
import healthcare_management_system.test_application.repository.*;
import healthcare_management_system.test_application.service.LaboratoryService;
import healthcare_management_system.test_application.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/laboratory")
public class LaboratoryController {

    @Autowired
    LaboratoryService laboratoryService;

    @Autowired
    PatientService patientService;

    @Autowired
    TechnicianRepository technicianRepository;

    @Autowired CptRepository cptRepository;
    @Autowired CbctRepository cbctRepository;
    @Autowired DfptRepository dfptRepository;
    @Autowired KftRepository kftRepository;
    @Autowired LftRepository lftRepository;
    @Autowired LptRepository lptRepository;
    @Autowired TftRepository tftRepository;

    int patientId;
    @GetMapping("/login")
    public String login(Model model){
        LabTechnician labTechnician = new LabTechnician();
        model.addAttribute("labTechnician",labTechnician);
        return "laboratory/login-page.html";
    }

    @PostMapping("/login-validation")
    public String login_validation(@ModelAttribute("labTechnician") LabTechnician labTechnician,Model model){
        int userInp = labTechnician.getLabTechnicianId();
        LabTechnician validatorObj = laboratoryService.password(userInp);
        System.out.println(labTechnician.getPassword());
        System.out.println(validatorObj.getPassword());
        if (labTechnician.getPassword().equals(validatorObj.getPassword()) ){
            model.addAttribute("name",labTechnician.getName());
            return "laboratory/lab-technician-dashboard.html";
        }
        else {
            return "labTechnician-invalid-credentials.html";
        }
    }

    //Coagulation profile test
    @GetMapping("/list/cpt")
    public String cpt(Model model){
        List<CoagulationProfileTest>completedCptList = cptRepository.findByStatus("completed");
        List<CoagulationProfileTest>pendingCptList = cptRepository.findByStatus("pending");
        model.addAttribute("completedCptList",completedCptList);
        model.addAttribute("pendingCptList",pendingCptList);
        return "laboratory/cpt-list.html";
    }
    int cpt_Id;
    @PostMapping("/test-cpt/{cptId}")
    public String testCpt(@PathVariable( value = "cptId")int cptId, Model model){
        CoagulationProfileTest coagulationProfileTest = laboratoryService.findCptById(cptId);
        cpt_Id = cptId;
        Patients patient = patientService.password(coagulationProfileTest.getPatientId());
        String patientName = patient.getFirstName()+" "+patient.getLastName();
        int age = patient.getAge();
        patientId = patient.getPatientId();
        model.addAttribute("coagulationProfileTest",coagulationProfileTest);
        model.addAttribute("patientName",patientName);
        model.addAttribute("age",age);
        return "laboratory/cpt-form.html";
    }
    @PostMapping("/save-cpt")
    public String coagulationProfileTest(@ModelAttribute("coagulationProfileTest") CoagulationProfileTest coagulationProfileTest,
                                         Model model) {
        coagulationProfileTest.setCptId(cpt_Id);
        coagulationProfileTest.setDate(laboratoryService.findCptById(cpt_Id).getDate());
        coagulationProfileTest.setPatientId(patientId);
        LocalDate localDate = LocalDate.now();
        coagulationProfileTest.setDateOfCompletion(String.valueOf(localDate));
        coagulationProfileTest.setStatus("completed");
        laboratoryService.saveCpt(coagulationProfileTest);
        model.addAttribute("testName","Coagulation profile test");
        model.addAttribute("date",coagulationProfileTest.getDate());
        model.addAttribute("name",patientService.password(coagulationProfileTest.getPatientId()).getFirstName()+" "+
                patientService.password(coagulationProfileTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @GetMapping("/print-cpt/{cptId}")
    public ResponseEntity<InputStreamResource>  printCpt(@PathVariable( value = "cptId")int cptId, Model model){
        CoagulationProfileTest coagulationProfileTest = laboratoryService.findCptById(cptId);
        Patients patient = patientService.password(coagulationProfileTest.getPatientId());
        ByteArrayInputStream pdf = laboratoryService.downloadCpt(coagulationProfileTest, patient);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;file=prescription.pdf");
        return ResponseEntity.ok().header(String.valueOf(httpHeaders)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }

    //Complete blood count test
    @GetMapping("/list/cbct")
    public String cbct(Model model){
        List<CompleteBloodCountTest>completedCbctList = cbctRepository.findByStatus("completed");
        List<CompleteBloodCountTest>pendingCbctList = cbctRepository.findByStatus("pending");
        model.addAttribute("completedCbctList",completedCbctList);
        model.addAttribute("pendingCbctList",pendingCbctList);
        return "laboratory/cbct-list.html";
    }
    int cbct_id;
    @PostMapping("/test-cbct/{cbcId}")
    public String testCbct(@PathVariable( value = "cbcId")int cbcId, Model model){
        CompleteBloodCountTest completeBloodCountTest = laboratoryService.findCbctById(cbcId);
        cbct_id = cbcId;
        Patients patient = patientService.password(completeBloodCountTest.getPatientId());
        String patientName = patient.getFirstName()+" "+patient.getLastName();
        int age = patient.getAge();
        patientId = patient.getPatientId();
        model.addAttribute("completeBloodCountTest",completeBloodCountTest);
        model.addAttribute("patientName",patientName);
        model.addAttribute("age",age);
        return "laboratory/cbct-form.html";
    }
    @PostMapping("/save-cbct")
    public String completeBloodCountTest (@ModelAttribute("completeBloodCountTest") CompleteBloodCountTest completeBloodCountTest, Model model) {
        completeBloodCountTest.setCbcId(cbct_id);
        completeBloodCountTest.setDate(laboratoryService.findCbctById(cbct_id).getDate());
        completeBloodCountTest.setPatientId(patientId);
        LocalDate localDate = LocalDate.now();
        completeBloodCountTest.setDateOfCompletion(String.valueOf(localDate));
        completeBloodCountTest.setStatus("completed");
        laboratoryService.saveCbct(completeBloodCountTest);
        model.addAttribute("testName","Complete blood count test");
        model.addAttribute("date",completeBloodCountTest.getDate());
        model.addAttribute("name",patientService.password(completeBloodCountTest.getPatientId()).getFirstName()+" "+
                patientService.password(completeBloodCountTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @GetMapping("/print-cbct/{cbcId}")
    public ResponseEntity<InputStreamResource>  printCbct(@PathVariable( value = "cbcId")int cbcId, Model model){
        CompleteBloodCountTest completeBloodCountTest = laboratoryService.findCbctById(cbcId);
        Patients patient = patientService.password(completeBloodCountTest.getPatientId());
        ByteArrayInputStream pdf = laboratoryService.downloadCbct(completeBloodCountTest, patient);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;file=prescription.pdf");
        return ResponseEntity.ok().header(String.valueOf(httpHeaders)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }
    //Dengue fever panel test
    @GetMapping("/list/dfpt")
    public String dfpt(Model model){
        List<DengueFeverPanelTest>completedDfptList = dfptRepository.findByStatus("completed");
        List<DengueFeverPanelTest>pendingDfptList = dfptRepository.findByStatus("pending");
        model.addAttribute("completedDfptList",completedDfptList);
        model.addAttribute("pendingDfptList",pendingDfptList);
        return "laboratory/dfpt-list.html";
    }
    int dfpt_id;
    @PostMapping("/test-dfpt/{dftId}")
    public String testDfpt(@PathVariable( value = "dftId")int dftId, Model model){
        DengueFeverPanelTest dengueFeverPanelTest = laboratoryService.findDfptById(dftId);
        dfpt_id = dftId;
        Patients patient = patientService.password(dengueFeverPanelTest.getPatientId());
        String patientName = patient.getFirstName()+" "+patient.getLastName();
        int age = patient.getAge();
        patientId = patient.getPatientId();
        model.addAttribute("dengueFeverPanelTest",dengueFeverPanelTest);
        model.addAttribute("patientName",patientName);
        model.addAttribute("age",age);
        return "laboratory/dfpt-form.html";
    }
    @PostMapping("/save-dfpt")
    public String dengueFeverPanelTest(@ModelAttribute("dengueFeverPanelTest") DengueFeverPanelTest dengueFeverPanelTest,
                                       Model model) {
        dengueFeverPanelTest.setDftId(dfpt_id);
        dengueFeverPanelTest.setDate(laboratoryService.findDfptById(dfpt_id).getDate());
        dengueFeverPanelTest.setPatientId(patientId);
        LocalDate localDate = LocalDate.now();
        dengueFeverPanelTest.setDateOfCompletion(String.valueOf(localDate));
        dengueFeverPanelTest.setStatus("completed");
        laboratoryService.saveDfpt(dengueFeverPanelTest);
        model.addAttribute("testName","Dengue fever panel test");
        model.addAttribute("date",dengueFeverPanelTest.getDate());
        model.addAttribute("name",patientService.password(dengueFeverPanelTest.getPatientId()).getFirstName()+" "+
                patientService.password(dengueFeverPanelTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @GetMapping("/print-dfpt/{dftId}")
    public ResponseEntity<InputStreamResource>  printDfpt(@PathVariable( value = "dftId")int dftId, Model model){
        DengueFeverPanelTest dengueFeverPanelTest = laboratoryService.findDfptById(dftId);
        Patients patient = patientService.password(dengueFeverPanelTest.getPatientId());
        ByteArrayInputStream pdf = laboratoryService.downloadDfpt(dengueFeverPanelTest, patient);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;file=prescription.pdf");
        return ResponseEntity.ok().header(String.valueOf(httpHeaders)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }
    //Kidney function test
    @GetMapping("/list/kft")
    public String kft(Model model){
        List<KidneyFunctionTest>completedKftList = kftRepository.findByStatus("completed");
        List<KidneyFunctionTest>pendingKftList = kftRepository.findByStatus("pending");
        model.addAttribute("completedKftList",completedKftList);
        model.addAttribute("pendingKftList",pendingKftList);
        return "laboratory/kft-list.html";
    }
    int kft_id;
    @PostMapping("/test-kft/{kftId}")
    public String testKft(@PathVariable( value = "kftId")int kftId, Model model){
        KidneyFunctionTest kidneyFunctionTest = laboratoryService.findKftById(kftId);
        kft_id = kftId;
        Patients patient = patientService.password(kidneyFunctionTest.getPatientId());
        String patientName = patient.getFirstName()+" "+patient.getLastName();
        int age = patient.getAge();
        patientId = patient.getPatientId();
        model.addAttribute("kidneyFunctionTest",kidneyFunctionTest);
        model.addAttribute("patientName",patientName);
        model.addAttribute("age",age);
        return "laboratory/kft-form.html";
    }
    @PostMapping("/save-kft")
    public String kidneyFunctionTest(@ModelAttribute("kidneyFunctionTest")KidneyFunctionTest kidneyFunctionTest,
                                     Model model) {
        kidneyFunctionTest.setKftId(kft_id);
        kidneyFunctionTest.setDate(laboratoryService.findKftById(kft_id).getDate());
        kidneyFunctionTest.setPatientId(patientId);
        LocalDate localDate = LocalDate.now();
        kidneyFunctionTest.setDateOfCompletion(String.valueOf(localDate));
        kidneyFunctionTest.setStatus("completed");
        laboratoryService.saveKft(kidneyFunctionTest);
        model.addAttribute("testName","Kidney function test");
        model.addAttribute("date",kidneyFunctionTest.getDate());
        model.addAttribute("name",patientService.password(kidneyFunctionTest.getPatientId()).getFirstName()+" "+
                patientService.password(kidneyFunctionTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @GetMapping("/print-kft/{kftId}")
    public ResponseEntity<InputStreamResource>  printKft(@PathVariable( value = "kftId")int kftId, Model model){
        KidneyFunctionTest kidneyFunctionTest = laboratoryService.findKftById(kftId);
        Patients patient = patientService.password(kidneyFunctionTest.getPatientId());
        ByteArrayInputStream pdf = laboratoryService.downloadKft(kidneyFunctionTest, patient);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;file=prescription.pdf");
        return ResponseEntity.ok().header(String.valueOf(httpHeaders)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }


    //Lipid profile test
    @GetMapping("/list/lpt")
    public String lpt(Model model){
        List<LipidProfileTest>completedLptList = lptRepository.findByStatus("completed");
        List<LipidProfileTest>pendingLptList = lptRepository.findByStatus("pending");
        model.addAttribute("completedLptList",completedLptList);
        model.addAttribute("pendingLptList",pendingLptList);
        return "laboratory/lpt-list.html";
    }
    int lpt_id;
    @PostMapping("/test-lpt/{lptId}")
    public String testLpt(@PathVariable( value = "lptId")int lptId, Model model){
        LipidProfileTest lipidProfileTest = laboratoryService.findLptById(lptId);
        lpt_id = lptId;
        Patients patient = patientService.password(lipidProfileTest.getPatientId());
        String patientName = patient.getFirstName()+" "+patient.getLastName();
        int age = patient.getAge();
        patientId = patient.getPatientId();
        model.addAttribute("lipidProfileTest",lipidProfileTest);
        model.addAttribute("patientName",patientName);
        model.addAttribute("age",age);
        return "laboratory/lpt-form.html";
    }
    @PostMapping("/save-lpt")
    public String lipidProfileTest(@ModelAttribute("lipidProfileTest")LipidProfileTest lipidProfileTest,
                                   Model model) {
        lipidProfileTest.setLptId(lpt_id);
        lipidProfileTest.setDate(laboratoryService.findLptById(lpt_id).getDate());
        lipidProfileTest.setPatientId(patientId);
        LocalDate localDate = LocalDate.now();
        lipidProfileTest.setDateOfCompletion(String.valueOf(localDate));
        lipidProfileTest.setStatus("completed");
        laboratoryService.saveLpt(lipidProfileTest);
        model.addAttribute("testName","Lipid profile test");
        model.addAttribute("date",lipidProfileTest.getDate());
        model.addAttribute("name",patientService.password(lipidProfileTest.getPatientId()).getFirstName()+" "+
                patientService.password(lipidProfileTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @GetMapping("/print-lpt/{lptId}")
    public ResponseEntity<InputStreamResource>  printLpt(@PathVariable( value = "lptId")int lptId, Model model){
        LipidProfileTest lipidProfileTest = laboratoryService.findLptById(lptId);
        Patients patient = patientService.password(lipidProfileTest.getPatientId());
        ByteArrayInputStream pdf = laboratoryService.downloadLpt(lipidProfileTest, patient);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;file=prescription.pdf");
        return ResponseEntity.ok().header(String.valueOf(httpHeaders)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }

    //Liver function test
    @GetMapping("/list/lft")
    public String lft(Model model){
        List<LiverFunctionTest>completedLftList = lftRepository.findByStatus("completed");
        List<LiverFunctionTest>pendingLftList = lftRepository.findByStatus("pending");
        model.addAttribute("completedLftList",completedLftList);
        model.addAttribute("pendingLftList",pendingLftList);
        return "laboratory/lft-list.html";
    }
    int lft_id;
    @PostMapping("/test-lft/{lftId}")
    public String testLft(@PathVariable( value = "lftId")int lftId, Model model){
        LiverFunctionTest liverFunctionTest = laboratoryService.findLftById(lftId);
        lft_id = lftId;
        Patients patient = patientService.password(liverFunctionTest.getPatientId());
        String patientName = patient.getFirstName()+" "+patient.getLastName();
        int age = patient.getAge();
        patientId = patient.getPatientId();
        model.addAttribute("liverFunctionTest",liverFunctionTest);
        model.addAttribute("patientName",patientName);
        model.addAttribute("age",age);
        return "laboratory/lft-form.html";
    }
    @PostMapping("/save-lft")
    public String liverFunctionTest(@ModelAttribute("liverFunctionTest")LiverFunctionTest liverFunctionTest,
                                    Model model) {
        liverFunctionTest.setLftId(lft_id);
        liverFunctionTest.setDate(laboratoryService.findLftById(lft_id).getDate());
        liverFunctionTest.setPatientId(patientId);
        LocalDate localDate = LocalDate.now();
        liverFunctionTest.setStatus("completed");
        liverFunctionTest.setDateOfCompletion(String.valueOf(localDate));
        laboratoryService.saveLft(liverFunctionTest);
        model.addAttribute("testName","Liver function test");
        model.addAttribute("date",liverFunctionTest.getDate());
        model.addAttribute("name",patientService.password(liverFunctionTest.getPatientId()).getFirstName()+" "+
                patientService.password(liverFunctionTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @GetMapping("/print-lft/{lftId}")
    public ResponseEntity<InputStreamResource>  printLft(@PathVariable( value = "lftId")int lftId, Model model){
        LiverFunctionTest liverFunctionTest = laboratoryService.findLftById(lftId);
        Patients patient = patientService.password(liverFunctionTest.getPatientId());
        ByteArrayInputStream pdf = laboratoryService.downloadLft(liverFunctionTest, patient);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;file=prescription.pdf");
        return ResponseEntity.ok().header(String.valueOf(httpHeaders)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }

    //Thyroid Function Test
    @GetMapping("/list/tft")
    public String tft(Model model){
        List<ThyroidFunctionTest>completedTftList = tftRepository.findByStatus("completed");
        List<ThyroidFunctionTest>pendingTftList = tftRepository.findByStatus("pending");
        model.addAttribute("completedTftList",completedTftList);
        model.addAttribute("pendingTftList",pendingTftList);
        return "laboratory/tft-list.html";
    }
    int tft_id;
    @PostMapping("/test-tft/{tftId}")
    public String testTft(@PathVariable( value = "tftId")int tftId, Model model){
        ThyroidFunctionTest thyroidFunctionTest = laboratoryService.findTftById(tftId);
        tft_id = tftId;
        Patients patient = patientService.password(thyroidFunctionTest.getPatientId());
        String patientName = patient.getFirstName()+" "+patient.getLastName();
        int age = patient.getAge();
        patientId = patient.getPatientId();
        model.addAttribute("thyroidFunctionTest",thyroidFunctionTest);
        model.addAttribute("patientName",patientName);
        model.addAttribute("age",age);
        return "laboratory/tft-form.html";
    }
    @PostMapping("/save-tft")
    public String thyroidFunctionTest(@ModelAttribute("thyroidFunctionTest")ThyroidFunctionTest thyroidFunctionTest,
                                      Model model) {
        thyroidFunctionTest.setTftId(tft_id);
        thyroidFunctionTest.setDate(laboratoryService.findTftById(tft_id).getDate());
        thyroidFunctionTest.setPatientId(patientId);
        LocalDate localDate = LocalDate.now();
        thyroidFunctionTest.setDateOfCompletion(String.valueOf(localDate));
        thyroidFunctionTest.setStatus("completed");
        laboratoryService.saveTft(thyroidFunctionTest);
        model.addAttribute("testName","Thyroid function test");
        model.addAttribute("date",thyroidFunctionTest.getDate());
        model.addAttribute("name",patientService.password(thyroidFunctionTest.getPatientId()).getFirstName()+" "+
                patientService.password(thyroidFunctionTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @GetMapping("/print-tft/{tftId}")
    public ResponseEntity<InputStreamResource>  printTft(@PathVariable( value = "tftId")int tftId, Model model){
        ThyroidFunctionTest thyroidFunctionTest = laboratoryService.findTftById(tftId);
        Patients patient = patientService.password(thyroidFunctionTest.getPatientId());
        ByteArrayInputStream pdf = laboratoryService.downloadTft(thyroidFunctionTest, patient);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;file=prescription.pdf");
        return ResponseEntity.ok().header(String.valueOf(httpHeaders)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }

}
