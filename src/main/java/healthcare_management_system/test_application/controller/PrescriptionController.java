package healthcare_management_system.test_application.controller;

import healthcare_management_system.test_application.model.*;
import healthcare_management_system.test_application.repository.PrescriptionRepository;
import healthcare_management_system.test_application.service.AppointmentService;
import healthcare_management_system.test_application.service.DoctorService;
import healthcare_management_system.test_application.service.PatientService;
import healthcare_management_system.test_application.service.PrescriptionService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    PatientService patientService;
    @Autowired
    DoctorController doctorController;
    @Autowired
    PrescriptionService prescriptionService;
    Prescription prescriptionTemp;
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;

    Appointment appointment;

    @PostMapping("/add/{appointmentId}")
    public String create(@PathVariable( value = "appointmentId")int appointmentId,@ModelAttribute("prescription") Prescription prescription, Model model){

        appointment = appointmentService.findAppointment(appointmentId);

        Patients patients = patientService.password(appointment.getPatientId());
        Doctor doctor = doctorController.forHomePage;
        System.out.println("patientId"+appointment.getPatientId());
        System.out.println("doctor.getDoctorId()"+doctor.getDoctorId());
        System.out.println("prescription.getAppointmentId()"+prescription.getAppointmentId());
        prescription.setPatientId(appointment.getPatientId());
        prescription.setDoctorId(doctor.getDoctorId());
        prescription.setAppointmentId(prescription.getAppointmentId());
        prescriptionTemp = prescription;
        Ai ai = new Ai();
        Doctor doctor1 = doctorService.password(doctor.getDoctorId());
        model.addAttribute("Ai",ai);
        model.addAttribute("doctor",doctor1);
        model.addAttribute("patients",patients);
        model.addAttribute("prescription",prescription);
        return "prescription-page.html";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("prescription") Prescription prescription, Model model){
        prescriptionTemp.setBp(prescription.getBp());
        prescriptionTemp.setDiagnosis(prescription.getDiagnosis());
        prescriptionTemp.setMedication(prescription.getMedication());
        prescriptionTemp.setSpo2(prescription.getSpo2());
        prescriptionTemp.setPulse(prescription.getPulse());
        prescriptionTemp.setNotes(prescription.getNotes());
        prescriptionTemp.setWeight(prescription.getWeight());
        prescriptionService.save(prescriptionTemp);

        System.out.println(prescriptionTemp.getPatientId());
        System.out.println(appointment.getAppointmentId());
        System.out.println(appointment.getConsulted());
        appointment.setConsulted("consulted");
        System.out.println(appointment.getConsulted());

        appointmentService.save(appointment);

        Doctor doctor = doctorService.password(appointment.getDoctorId());
        Patients patients = patientService.password(appointment.getPatientId());

        prescriptionService.sendPrescription(prescription,patients, doctor,appointment);

        return "prescription-successful.html";
    }

    @GetMapping("/download/{prescriptionId}")
    public ResponseEntity<InputStreamResource> pdfDownload(@PathVariable( value = "prescriptionId")int prescriptionId,
                                                        @ModelAttribute("prescription") Prescription prescription,
                                                        Model model){

        Prescription prescription1 = new Prescription();
        prescription1 = prescriptionService.getByID(prescriptionId);
        ByteArrayInputStream pdf = prescriptionService.download(prescription1);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;file=prescription.pdf");
        return ResponseEntity.ok().header(String.valueOf(httpHeaders)).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
        //tab name
        //rendering html content

    }

    @GetMapping("/view/{prescriptionId}")
    public String viewPrescription(@PathVariable( value = "prescriptionId")int prescriptionId,
                                                           @ModelAttribute("prescription") Prescription prescription,
                                                           Model model){

        Prescription prescription1 = new Prescription();
        prescription1 = prescriptionService.getByID(prescriptionId);
        model.addAttribute("prescription",prescription1);
        return "prescription-view.html";
    }


}
