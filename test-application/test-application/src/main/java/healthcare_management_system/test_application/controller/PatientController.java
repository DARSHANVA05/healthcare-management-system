package healthcare_management_system.test_application.controller;

import healthcare_management_system.test_application.dto.PrescriptionDTO;
import healthcare_management_system.test_application.model.*;
import healthcare_management_system.test_application.repository.DoctorRepository;
import healthcare_management_system.test_application.repository.PrescriptionRepository;
import healthcare_management_system.test_application.service.AppointmentService;
import healthcare_management_system.test_application.service.DoctorService;
import healthcare_management_system.test_application.service.LaboratoryService;
import healthcare_management_system.test_application.service.PatientService;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    LaboratoryService laboratoryService;
    Patients tempAccount;
    int tempOtp;

    public int patientIdforAppointment;

    public int getPatientIdforAppointment() {
        return patientIdforAppointment;
    }

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @GetMapping("/account-creation")
    public String createAccount(Model model){
        Patients patients = new Patients();
        model.addAttribute("patients",patients);
        return "account-creation-form.html";
    }

    @PostMapping("/validation")
    public String validation(@ModelAttribute("patients") Patients patients, Model model){
        tempAccount = patients;

        Random random = new Random();
        int otp = random.nextInt(9999 - 1000 + 1) + 1000;
        tempOtp = otp;
        System.out.println("OTP: "+tempOtp);
        try {
            int patientId = patients.getPatientId();
            patientService.otpValidation(patientId,patients,tempOtp);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (MailConnectException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("patients",patients);
        return "validation.html";
    }

    @PostMapping("/password")
    public String pinGeneration(@ModelAttribute("patients") Patients patients, Model model){

        if (tempOtp == patients.getOtp()){
            System.out.println("Correct otp");
            tempAccount.setOtp(patients.getOtp());
            return "password.html";
        }
        else {
            System.out.println("Incorrect otp");
            return "";
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("patients") Patients patients, Model model){
        tempAccount.setPassword(patients.getPassword());
        System.out.println(tempAccount.getPatientId());
        int patient_id = patientService.save(tempAccount);
        model.addAttribute("patients",tempAccount);
        patientService.confirmation(patient_id,tempAccount);
        return "success-page.html";
    }

    @GetMapping("/login")
    public String login(Model model){
        Patients patients_login = new Patients();
        model.addAttribute("patients",patients_login);
        return "patient-login.html";
    }


    @PostMapping("/login-validation")
    public String login_validation(@ModelAttribute("patients") Patients patients,Model model){
        int userInp = patients.getPatientId();
        Patients validatorObj = patientService.password(userInp);
        System.out.println(patients.getPassword());
        System.out.println(validatorObj.getPassword());
        if (patients.getPassword().equals(validatorObj.getPassword()) ){
            System.out.println("Correct password");
            patientIdforAppointment = patients.getPatientId();
            List<Doctor> doctorList = doctorRepository.findAll();
            model.addAttribute("doctorList",doctorList);
            model.addAttribute("patients",patients);
            model.addAttribute("patientName",validatorObj.getFirstName()+" "+validatorObj.getLastName());
            return "patients-homepage.html";
        }
        else {
            return "patient-invalid-credentials.html";
        }
    }

    @GetMapping("/prescriptions/{patientId}")
    public String prescriptionList(@PathVariable( value = "patientId")int patientId,@ModelAttribute("patients") Patients patients, Model model){

        List<Prescription>prescriptionList = prescriptionRepository.findByPatientId(patientId);
        System.out.println(prescriptionList);
        List<PrescriptionDTO>prescriptionDTOList = new ArrayList<>();

        for(Prescription prescription: prescriptionList){
            PrescriptionDTO prescriptionDTO = new PrescriptionDTO();

            Doctor doctor = doctorService.password(prescription.getDoctorId());
            Appointment appointment = appointmentService.findAppointment(prescription.getAppointmentId());
            prescriptionDTO.setPrescriptionId(prescription.getPrescription());
            prescriptionDTO.setDate(appointment.getDate());
            prescriptionDTO.setFirstName(doctor.getFirstName());
            prescriptionDTO.setLastName(doctor.getLastName());
            prescriptionDTO.setSpecialisation(doctor.getMedicalExpertise());
            prescriptionDTOList.add(prescriptionDTO);
        }
        model.addAttribute("prescriptionDTOList",prescriptionDTOList);
        return "prescription-list.html";
    }

    @GetMapping("/medicalRecords/{patientId}")
    public String medicalRecords(@PathVariable( value = "patientId")int patientId,@ModelAttribute("patients") Patients patients, Model model){

        List<Prescription>prescriptionList = prescriptionRepository.findByPatientId(patientId);
        System.out.println(prescriptionList);
        List<PrescriptionDTO>prescriptionDTOList = new ArrayList<>();

        for(Prescription prescription: prescriptionList){
            PrescriptionDTO prescriptionDTO = new PrescriptionDTO();

            Doctor doctor = doctorService.password(prescription.getDoctorId());
            Appointment appointment = appointmentService.findAppointment(prescription.getAppointmentId());
            prescriptionDTO.setPrescriptionId(prescription.getPrescription());
            prescriptionDTO.setDate(appointment.getDate());
            prescriptionDTO.setFirstName(doctor.getFirstName());
            prescriptionDTO.setLastName(doctor.getLastName());
            prescriptionDTO.setSpecialisation(doctor.getMedicalExpertise());
            prescriptionDTOList.add(prescriptionDTO);
        }
        model.addAttribute("prescriptionDTOList",prescriptionDTOList);
        return "medicalRecords-list.html";
    }

    @GetMapping("/cpt")
    public String coagulationProfileTest(Model model) {
        return getLaboratoryTests(model, "cpt");
    }
    @GetMapping("/cbct")
    public String completeBloodCountTest(Model model) {
        return getLaboratoryTests(model, "cbct");
    }
    @GetMapping("/dfpt")
    public String dengueFeverPanelTest(Model model) {
        return getLaboratoryTests(model, "dfpt");
    }
    @GetMapping("/kft")
    public String kidneyFunctionTest(Model model) {
        return getLaboratoryTests(model, "kft");
    }
    @GetMapping("/lpt")
    public String lipidProfileTest(Model model) {
        return getLaboratoryTests(model, "lpt");
    }
    @GetMapping("/lft")
    public String liverFunctionTest(Model model) {
        return getLaboratoryTests(model, "lft");
    }
    @GetMapping("/tft")
    public String thyroidFunctionTest(Model model) {
        return getLaboratoryTests(model, "tft");
    }
    private String getLaboratoryTests(Model model, String testName) {

        switch (testName) {
            case "cpt":
                CoagulationProfileTest coagulationProfileTest = new CoagulationProfileTest();
                coagulationProfileTest.setPatientId(patientIdforAppointment);
                model.addAttribute("coagulationProfileTest", coagulationProfileTest);
                return "patients/cpt-booking.html";

            case "cbct":
                CompleteBloodCountTest completeBloodCountTest = new CompleteBloodCountTest();
                completeBloodCountTest.setPatientId(patientIdforAppointment);
                model.addAttribute("completeBloodCountTest", completeBloodCountTest);
                return "patients/cbct-booking.html";

            case "dfpt":
                DengueFeverPanelTest dengueFeverPanelTest = new DengueFeverPanelTest();
                dengueFeverPanelTest.setPatientId(patientIdforAppointment);
                model.addAttribute("dengueFeverPanelTest", dengueFeverPanelTest);
                return "patients/dfpt-booking.html";

            case "kft":
                KidneyFunctionTest kidneyFunctionTest = new KidneyFunctionTest();
                kidneyFunctionTest.setPatientId(patientIdforAppointment);
                model.addAttribute("kidneyFunctionTest", kidneyFunctionTest);
                return "patients/kft-booking.html";

            case "lpt":
                LipidProfileTest lipidProfileTest = new LipidProfileTest();
                lipidProfileTest.setPatientId(patientIdforAppointment);
                model.addAttribute("lipidProfileTest", lipidProfileTest);
                return "patients/lpt-booking.html";

            case "lft":
                LiverFunctionTest liverFunctionTest = new LiverFunctionTest();
                liverFunctionTest.setPatientId(patientIdforAppointment);
                model.addAttribute("liverFunctionTest", liverFunctionTest);
                return "patients/lft-booking.html";

            case "tft":
                ThyroidFunctionTest thyroidFunctionTest = new ThyroidFunctionTest();
                thyroidFunctionTest.setPatientId(patientIdforAppointment);
                model.addAttribute("thyroidFunctionTest", thyroidFunctionTest);
                return "patients/tft-booking.html";

            default:
                return "error.html";
        }
    }

    @PostMapping("/save-cpt")
    public String coagulationProfileTest(@ModelAttribute("coagulationProfileTest") CoagulationProfileTest coagulationProfileTest,
                                         Model model) {
        coagulationProfileTest.setPatientId(patientIdforAppointment);
        coagulationProfileTest.setStatus("pending");
        laboratoryService.saveCpt(coagulationProfileTest);
        model.addAttribute("testName","Coagulation profile test");
        model.addAttribute("date",coagulationProfileTest.getDate());
        model.addAttribute("name",patientService.password(coagulationProfileTest.getPatientId()).getFirstName()+" "+
                patientService.password(coagulationProfileTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @PostMapping("/save-cbct")
    public String completeBloodCountTest(@ModelAttribute("completeBloodCountTest") CompleteBloodCountTest completeBloodCountTest,
                                         Model model) {
        completeBloodCountTest.setPatientId(patientIdforAppointment);
        completeBloodCountTest.setStatus("pending");
        laboratoryService.saveCbct(completeBloodCountTest);
        model.addAttribute("testName","Complete blood count test");
        model.addAttribute("date",completeBloodCountTest.getDate());
        model.addAttribute("name",patientService.password(completeBloodCountTest.getPatientId()).getFirstName()+" "+
                patientService.password(completeBloodCountTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @PostMapping("/save-dfpt")
    public String dengueFeverPanelTest(@ModelAttribute("dengueFeverPanelTest") DengueFeverPanelTest dengueFeverPanelTest,
                                       Model model) {
        dengueFeverPanelTest.setPatientId(patientIdforAppointment);
        dengueFeverPanelTest.setStatus("pending");
        laboratoryService.saveDfpt(dengueFeverPanelTest);
        model.addAttribute("testName","Dengue fever panel test");
        model.addAttribute("date",dengueFeverPanelTest.getDate());
        model.addAttribute("name",patientService.password(dengueFeverPanelTest.getPatientId()).getFirstName()+" "+
                patientService.password(dengueFeverPanelTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @PostMapping("/save-kft")
    public String kidneyFunctionTest(@ModelAttribute("kidneyFunctionTest")KidneyFunctionTest kidneyFunctionTest,
                                     Model model) {
        kidneyFunctionTest.setPatientId(patientIdforAppointment);
        kidneyFunctionTest.setStatus("pending");
        laboratoryService.saveKft(kidneyFunctionTest);
        model.addAttribute("testName","Kidney function test");
        model.addAttribute("date",kidneyFunctionTest.getDate());
        model.addAttribute("name",patientService.password(kidneyFunctionTest.getPatientId()).getFirstName()+" "+
                patientService.password(kidneyFunctionTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @PostMapping("/save-lpt")
    public String lipidProfileTest(@ModelAttribute("lipidProfileTest")LipidProfileTest lipidProfileTest,
                                   Model model) {
        lipidProfileTest.setPatientId(patientIdforAppointment);
        lipidProfileTest.setStatus("pending");
        laboratoryService.saveLpt(lipidProfileTest);
        model.addAttribute("testName","Lipid profile test");
        model.addAttribute("date",lipidProfileTest.getDate());
        model.addAttribute("name",patientService.password(lipidProfileTest.getPatientId()).getFirstName()+" "+
                patientService.password(lipidProfileTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @PostMapping("/save-lft")
    public String liverFunctionTest(@ModelAttribute("liverFunctionTest")LiverFunctionTest liverFunctionTest,
                                    Model model) {
        liverFunctionTest.setPatientId(patientIdforAppointment);
        liverFunctionTest.setStatus("pending");
        laboratoryService.saveLft(liverFunctionTest);
        model.addAttribute("testName","Liver function test");
        model.addAttribute("date",liverFunctionTest.getDate());
        model.addAttribute("name",patientService.password(liverFunctionTest.getPatientId()).getFirstName()+" "+
                patientService.password(liverFunctionTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
    @PostMapping("/save-tft")
    public String thyroidFunctionTest(@ModelAttribute("thyroidFunctionTest")ThyroidFunctionTest thyroidFunctionTest,
                                      Model model) {
        thyroidFunctionTest.setPatientId(patientIdforAppointment);
        thyroidFunctionTest.setStatus("pending");
        laboratoryService.saveTft(thyroidFunctionTest);
        model.addAttribute("testName","Thyroid function test");
        model.addAttribute("date",thyroidFunctionTest.getDate());
        model.addAttribute("name",patientService.password(thyroidFunctionTest.getPatientId()).getFirstName()+" "+
                patientService.password(thyroidFunctionTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }

}
