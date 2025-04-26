package healthcare_management_system.test_application.controller;

import healthcare_management_system.test_application.dto.DoctorDto;
import healthcare_management_system.test_application.model.Appointment;
import healthcare_management_system.test_application.model.Doctor;
import healthcare_management_system.test_application.model.Patients;
import healthcare_management_system.test_application.model.Prescription;
import healthcare_management_system.test_application.repository.DoctorRepository;
import healthcare_management_system.test_application.service.AppointmentService;
import healthcare_management_system.test_application.service.DoctorService;
import healthcare_management_system.test_application.service.PatientService;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PatientController patientController;

    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;

    Doctor doctor;


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
        return "doctors-list.html";
    }

    @PostMapping("/book/{doctorId}")
    public String bookAppointment(@PathVariable( value = "doctorId")int doctorId, Model model){
        doctor = doctorService.password(doctorId);
        Appointment appointment = new Appointment();
        model.addAttribute("appointment",appointment);
        return "appointment-form.html";
    }

    @PostMapping("/delete/{appointmentId}")
    public String delete(@PathVariable( value = "appointmentId")int appointmentId, @ModelAttribute("prescription") Prescription prescription, Model model){
        model.addAttribute("appointment",appointmentService.findAppointment(appointmentId));
        model.addAttribute("doctorName",doctorService.password(appointmentService.findAppointment(appointmentId).getDoctorId()).getFirstName()
                                                            +" "+doctorService.password(appointmentService.findAppointment(appointmentId).getDoctorId()).getLastName());
        Patients patients = patientService.password(appointmentService.findAppointment(appointmentId).getPatientId());
        model.addAttribute("patientName",patients.getFirstName()+" "+patients.getLastName());
        appointmentService.delete(appointmentId);
        return "appointment-delete-success.html";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("appointment") Appointment appointment, Model model){
        appointment.setDoctorId(doctor.getDoctorId());
        appointment.setPatientId(patientController.patientIdforAppointment);
        Patients patients = patientService.password(patientController.patientIdforAppointment);
        appointment.setAmount(100);
        appointment.setPaymentStatus("Paid");
        appointment.setConsulted("not-consulted");
        appointment.setAttendance("absent");
        appointmentService.save(appointment);
        try {
            appointmentService.sendMail(patients,doctor,appointment);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (MailConnectException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("appointment",appointment);
        String doctorName = doctorService.password(appointment.getDoctorId()).getFirstName()+" "+doctorService.password(appointment.getDoctorId()).getLastName();
        model.addAttribute("doctor",doctorName);
        return "appointment-successful.html";
    }
}