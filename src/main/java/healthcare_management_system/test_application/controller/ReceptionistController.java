package healthcare_management_system.test_application.controller;

import healthcare_management_system.test_application.dto.AppointmentDto;
import healthcare_management_system.test_application.model.*;
import healthcare_management_system.test_application.repository.AppointmentRepository;
import healthcare_management_system.test_application.repository.DoctorRepository;
import healthcare_management_system.test_application.service.*;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {

    @Autowired
    ReceptionistService receptionistService;
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorRepository doctorRepository;

    int receptionistId;
    String password;
    private String name;
    Doctor doctor;
    private int flag=0;
    @GetMapping("/login")
    public String login(Model model){
        Receptionist receptionist = new Receptionist();
        model.addAttribute("receptionist",receptionist);
        return "receptionist-login.html";
    }


    @PostMapping("/login-validation")
    public String login_validation(@ModelAttribute("receptionist") Receptionist receptionist, Model model){
        Receptionist validatorObj = receptionistService.password(receptionist.getReceptionistId());
        if(flag==1){
            LocalDate localDate = LocalDate.now();
            String localDateText = String.valueOf(localDate);
            List<Appointment> appointmentList = appointmentRepository.findByDate(localDateText);

            List<AppointmentDto> appointmentDtoList = new ArrayList<>();
            for (Appointment appointment : appointmentList){
                if(appointment.getConsulted().equals("not-consulted")) {
                    Patients patients = patientService.password(appointment.getPatientId());
                    Doctor doctor = doctorService.password(appointment.getDoctorId());
                    AppointmentDto appointmentDto = new AppointmentDto(appointment.getAppointmentId(), patients.getFirstName() + " " + patients.getLastName(),
                            doctor.getFirstName() + " " + doctor.getLastName(),
                            appointment.getTime(),
                            patients.getPhoneNo(),
                            appointment.getAttendance());
                    appointmentDtoList.add(appointmentDto);
                }
            }

            List<Doctor> doctorList = doctorRepository.findAll();

            model.addAttribute("appointmentDtoList",appointmentDtoList);
            model.addAttribute("receptionistName", validatorObj.getName());
            model.addAttribute("doctorList",doctorList);
            name = validatorObj.getName();
            receptionistId = receptionist.getReceptionistId();
            password = receptionist.getPassword();
            Appointment appointment = null;
            model.addAttribute("appointment",appointment);
            return "receptionist-homepage.html";
        }


        if (receptionist.getPassword().equals(validatorObj.getPassword())){
            model.addAttribute("receptionist",receptionist);
            flag=1;
            LocalDate localDate = LocalDate.now();
            String localDateText = String.valueOf(localDate);
            List<Appointment> appointmentList = appointmentRepository.findByDate(localDateText);

            List<AppointmentDto> appointmentDtoList = new ArrayList<>();
            for (Appointment appointment : appointmentList){
                if(appointment.getConsulted().equals("not-consulted")) {
                    Patients patients = patientService.password(appointment.getPatientId());
                    Doctor doctor = doctorService.password(appointment.getDoctorId());
                    AppointmentDto appointmentDto = new AppointmentDto(appointment.getAppointmentId(), patients.getFirstName() + " " + patients.getLastName(),
                                             doctor.getFirstName() + " " + doctor.getLastName(),
                                                        appointment.getTime(),
                                                        patients.getPhoneNo(),
                                                        appointment.getAttendance());
                    appointmentDtoList.add(appointmentDto);
                }
            }

            List<Doctor> doctorList = doctorRepository.findAll();

            model.addAttribute("appointmentDtoList",appointmentDtoList);
            model.addAttribute("receptionistName", validatorObj.getName());
            model.addAttribute("doctorList",doctorList);
            name = validatorObj.getName();
            receptionistId = receptionist.getReceptionistId();
            password = receptionist.getPassword();
            Appointment appointment = null;
            model.addAttribute("appointment",appointment);
            return "receptionist-homepage.html";
        }
        else {
            return "receptionist-invalid-credentials.html";
        }
    }


    @PostMapping("/status/{appointmentId}")
    public String statusUpdate(@ModelAttribute("receptionist") Receptionist receptionist,
                               @PathVariable( value = "appointmentId")int appointmentId,
                               Model model){
        LocalDate localDate = LocalDate.now();
        String localDateText = String.valueOf(localDate);

        Appointment appointmentX = appointmentService.findAppointment(appointmentId);
        if (appointmentX.getAttendance().equals("absent"))
            appointmentX.setAttendance("waiting");
        else if (appointmentX.getAttendance().equals("waiting"))
            appointmentX.setAttendance("absent");
        appointmentRepository.save(appointmentX);

        List<Appointment> appointmentList = appointmentRepository.findByDate(localDateText);

        List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        for (Appointment appointment : appointmentList){
            if(appointment.getConsulted().equals("not-consulted")) {
                Patients patients = patientService.password(appointment.getPatientId());
                Doctor doctor = doctorService.password(appointment.getDoctorId());
                AppointmentDto appointmentDto = new AppointmentDto(appointment.getAppointmentId(), patients.getFirstName() + " " + patients.getLastName(),
                        doctor.getFirstName() + " " + doctor.getLastName(),
                        appointment.getTime(),
                        patients.getPhoneNo(),
                        appointment.getAttendance());
                appointmentDtoList.add(appointmentDto);
            }
        }
        model.addAttribute("receptionistName",name);
        model.addAttribute("appointmentDtoList",appointmentDtoList);
        Appointment appointment = null;
        model.addAttribute("appointment",appointment);
        return "receptionist-homepage.html";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute("appointment") Appointment appointment, Model model){
        appointment.setDoctorId(1);
        appointment.setAmount(100);
        appointment.setDescription("TBD");
        appointment.setPaymentStatus("Paid");
        appointment.setConsulted("not-consulted");
        appointment.setAttendance("absent");
        appointmentService.save(appointment);

        LocalDate localDate = LocalDate.now();
        String localDateText = String.valueOf(localDate);
        List<Appointment> appointmentList = appointmentRepository.findByDate(localDateText);

        List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        for (Appointment appointment_ : appointmentList){
            if(appointment_.getConsulted().equals("not-consulted")) {
                Patients patients = patientService.password(appointment_.getPatientId());
                Doctor doctor = doctorService.password(appointment_.getDoctorId());
                AppointmentDto appointmentDto = new AppointmentDto(appointment_.getAppointmentId(), patients.getFirstName() + " " + patients.getLastName(),
                        doctor.getFirstName() + " " + doctor.getLastName(),
                        appointment_.getTime(),
                        patients.getPhoneNo(),
                        appointment_.getAttendance());
                appointmentDtoList.add(appointmentDto);
            }
        }

        model.addAttribute("appointmentDtoList",appointmentDtoList);

        return "receptionist-homepage.html";
    }

    @PostMapping("/save-appointment")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment, Model model){
        appointment.setDoctorId(doctor.getDoctorId());
        appointment.setAmount(100);
        appointment.setPaymentStatus("Paid");
        appointment.setConsulted("not-consulted");
        appointment.setAttendance("absent");
        appointmentService.save(appointment);
        try {
            Patients patients = patientService.password(appointment.getPatientId());
            appointmentService.sendMail(patients,doctor,appointment);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (MailConnectException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("appointment",appointment);
        String patientName = patientService.password(appointment.getPatientId()).getFirstName()+" "+patientService.password(appointment.getPatientId()).getLastName();
        String doctorName = doctorService.password(appointment.getDoctorId()).getFirstName()+" "+doctorService.password(appointment.getDoctorId()).getLastName();
        model.addAttribute("doctor",doctorName);
        model.addAttribute("patientName",patientName);
        return "appointment-successful.html";
    }

    @GetMapping("/cardiologists")
    public String cardiologists(Model model) {
        return getDoctorsBySpecialisation(model, "Cardiologist", "Cardiologist");
    }

    @GetMapping("/neurologists")
    public String neurologists(Model model) {
        return getDoctorsBySpecialisation(model, "Neurologist", "Neurologist");
    }

    @GetMapping("/gastroenterologists")
    public String gastroenterologists(Model model) {
        return getDoctorsBySpecialisation(model, "Gastroenterologist", "Gastroenterologist");
    }

    @GetMapping("/orthopedics")
    public String orthopedics(Model model) {
        return getDoctorsBySpecialisation(model, "Orthopedic", "Orthopedic");
    }

    @GetMapping("/oncologists")
    public String oncologists(Model model) {
        return getDoctorsBySpecialisation(model, "Oncologist", "Oncologist");
    }

    @GetMapping("/gynecologists")
    public String gynecologists(Model model) {
        return getDoctorsBySpecialisation(model, "Gynecologist", "Gynecologist");
    }

    @GetMapping("/dermatologists")
    public String dermatologists(Model model) {
        return getDoctorsBySpecialisation(model, "Dermatologist", "Dermatologist");
    }

    @GetMapping("/ophthalmologists")
    public String ophthalmologists(Model model) {
        return getDoctorsBySpecialisation(model, "Ophthalmologist", "Ophthalmologist");
    }

    @GetMapping("/pediatricians")
    public String pediatricians(Model model) {
        return getDoctorsBySpecialisation(model, "Pediatrician", "Pediatrician");
    }

    @GetMapping("/endocrinologists")
    public String endocrinologists(Model model) {
        return getDoctorsBySpecialisation(model, "Endocrinologist", "Endocrinologist");
    }

    @GetMapping("/urologists")
    public String urologists(Model model) {
        return getDoctorsBySpecialisation(model, "Urologist", "Urologist");
    }

    @GetMapping("/nephrologists")
    public String nephrologists(Model model) {
        return getDoctorsBySpecialisation(model, "Nephrologist", "Nephrologist");
    }

    @GetMapping("/pulmonologists")
    public String pulmonologists(Model model) {
        return getDoctorsBySpecialisation(model, "Pulmonologist", "Pulmonologist");
    }

    @GetMapping("/rheumatologists")
    public String rheumatologists(Model model) {
        return getDoctorsBySpecialisation(model, "Rheumatologist", "Rheumatologist");
    }

    @GetMapping("/radiologists")
    public String radiologists(Model model) {
        return getDoctorsBySpecialisation(model, "Radiologist", "Radiologist");
    }

    @GetMapping("/neonatologists")
    public String neonatologists(Model model) {
        return getDoctorsBySpecialisation(model, "Neonatologist", "Neonatologist");
    }

    @GetMapping("/psychiatrists")
    public String psychiatrists(Model model) {
        return getDoctorsBySpecialisation(model, "Psychiatrist", "Psychiatrist");
    }

    @GetMapping("/dentists")
    public String dentists(Model model) {
        return getDoctorsBySpecialisation(model, "Dentist", "Dentist");
    }

    @GetMapping("/ent-specialists")
    public String entSpecialists(Model model) {
        return getDoctorsBySpecialisation(model, "ENT Specialist", "ENT Specialist");
    }

    private String getDoctorsBySpecialisation(Model model, String expertise, String specialisation) {
        List<Doctor> doctorDtoList = doctorRepository.findByMedicalExpertise(expertise);
        model.addAttribute("specialisation", specialisation);
        model.addAttribute("doctorDtoList", doctorDtoList);
        return "frontdesk/doctors-list.html";
    }

    @PostMapping("/book/{doctorId}")
    public String bookAppointment(@PathVariable( value = "doctorId")int doctorId, Model model){
        doctor = doctorService.password(doctorId);
        Appointment appointment = new Appointment();
        model.addAttribute("appointment",appointment);
        return "frontdesk/frontdesk-appointment.html";
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

        switch (testName){
            case "cpt" :CoagulationProfileTest coagulationProfileTest = new CoagulationProfileTest();
                        model.addAttribute("coagulationProfileTest",coagulationProfileTest);
                        return "laboratory/cpt-booking.html";

            case "cbct":CompleteBloodCountTest completeBloodCountTest = new CompleteBloodCountTest();
                        model.addAttribute("completeBloodCountTest",completeBloodCountTest);
                        return "laboratory/cbct-booking.html";

            case "dfpt":DengueFeverPanelTest dengueFeverPanelTest = new DengueFeverPanelTest();
                        model.addAttribute("dengueFeverPanelTest",dengueFeverPanelTest);
                        return "laboratory/dfpt-booking.html";

            case "kft" :KidneyFunctionTest kidneyFunctionTest = new KidneyFunctionTest();
                        model.addAttribute("kidneyFunctionTest",kidneyFunctionTest);
                        return "laboratory/kft-booking.html";

            case "lpt" :LipidProfileTest lipidProfileTest = new LipidProfileTest();
                        model.addAttribute("lipidProfileTest",lipidProfileTest);
                        return "laboratory/lpt-booking.html";

            case "lft" :LiverFunctionTest liverFunctionTest = new LiverFunctionTest();
                        model.addAttribute("liverFunctionTest",liverFunctionTest);
                        return "laboratory/lft-booking.html";

            case "tft" :ThyroidFunctionTest thyroidFunctionTest = new ThyroidFunctionTest();
                        model.addAttribute("thyroidFunctionTest",thyroidFunctionTest);
                        return "laboratory/tft-booking.html";

            default:return "error.html";
        }
    }
    @Autowired
    LaboratoryService laboratoryService;
    @PostMapping("/save-cpt")
    public String coagulationProfileTest(@ModelAttribute("coagulationProfileTest") CoagulationProfileTest coagulationProfileTest,
                                         Model model) {
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
        thyroidFunctionTest.setStatus("pending");
        laboratoryService.saveTft(thyroidFunctionTest);
        model.addAttribute("testName","Thyroid function test");
        model.addAttribute("date",thyroidFunctionTest.getDate());
        model.addAttribute("name",patientService.password(thyroidFunctionTest.getPatientId()).getFirstName()+" "+
                patientService.password(thyroidFunctionTest.getPatientId()).getLastName());
        return "frontdesk/lab-test-successful.html";
    }
}
