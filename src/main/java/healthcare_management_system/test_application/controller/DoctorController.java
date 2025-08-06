package healthcare_management_system.test_application.controller;

import healthcare_management_system.test_application.dto.PatientDto;
import healthcare_management_system.test_application.model.Appointment;
import healthcare_management_system.test_application.model.Doctor;
import healthcare_management_system.test_application.model.Patients;
import healthcare_management_system.test_application.repository.AppointmentRepository;
import healthcare_management_system.test_application.repository.PatientRepository;
import healthcare_management_system.test_application.service.DoctorService;
import healthcare_management_system.test_application.service.PatientService;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    Doctor tempAccount;
    private int tempOtp;

    Doctor forHomePage;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping("/account-creation")
    public String createAccount(Model model){
        Doctor doctor = new Doctor();
        model.addAttribute("doctor",doctor);
        return "doctor-account-creation.html";
    }

    @PostMapping("/validation")
    public String validation(@ModelAttribute("doctor") Doctor doctor, Model model){
        tempAccount = doctor;

        Random random = new Random();
        int otp = random.nextInt(9999 - 1000 + 1) + 1000;
        tempOtp = otp;
        System.out.println("OTP: "+tempOtp);
        try {
            doctorService.otpValidation(doctor,tempOtp);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (MailConnectException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("doctor",doctor);
        return "doctor-validation.html";
    }

    @PostMapping("/password")
    public String pinGeneration(@ModelAttribute("doctor") Doctor doctor, Model model){

        if (tempOtp == doctor.getOtp()){
            System.out.println("Correct otp");
            tempAccount.setOtp(doctor.getOtp());
            return "doctor-password.html";
        }
        else {
            System.out.println("Incorrect otp");
            return "";
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("doctor") Doctor doctor, Model model){
        tempAccount.setPassword(doctor.getPassword());
        doctorService.save(tempAccount);
        model.addAttribute("doctor",doctor);
        return "doctor-account.html";
    }

    @GetMapping("/login")
    public String login(Model model){
        Doctor doctor_login = new Doctor();
        model.addAttribute("doctor",doctor_login);
        return "doctor-login.html";
    }

    @PostMapping("/login-validation")
    public String login_validation(@ModelAttribute("doctor") Doctor doctor,Model model){
        int userInp = doctor.getDoctorId();
        Doctor validatorObj = doctorService.password(userInp);
        System.out.println(doctor.getPassword());
        System.out.println(validatorObj.getPassword());
        if (doctor.getPassword().equals(validatorObj.getPassword()) ){
            System.out.println("Correct password");
            forHomePage = doctor;

            List<Appointment>appointmentList = appointmentRepository.findByDoctorId(forHomePage.getDoctorId());
            model.addAttribute("doctor",doctor);
            model.addAttribute("appointmentList",appointmentList);

            List<PatientDto>patientDtoList = new ArrayList<>();
            for(Appointment appointment : appointmentList) {

                int patientId = appointment.getPatientId();
                Patients patients = patientService.password(patientId);
                PatientDto patientDTO = new PatientDto();

                patientDTO.setPatientId(patientId);
                patientDTO.setAge(patients.getAge());
                System.out.println("appointment.getAppointmentId() in doctor controller:"+appointment.getAppointmentId());
                patientDTO.setAppointmentId(appointment.getAppointmentId());
                patientDTO.setFirstName(patients.getFirstName());
                patientDTO.setLastName(patients.getLastName());
                patientDTO.setAge(patients.getAge());
                patientDTO.setDate(appointment.getDate());
                System.out.println(patientDTO.getFirstName());
                patientDTO.setAttendance(appointment.getAttendance());
                LocalDate localDate = LocalDate.now();
                String localDateText = String.valueOf(localDate);

                System.out.println(appointment.getAppointmentId());
                System.out.println(appointment.getConsulted());
                if(localDateText.equals(patientDTO.getDate()) && appointment.getConsulted().equals("not-consulted")){
                    System.out.println("Equal");
                    patientDtoList.add(patientDTO);
                }
            }

            model.addAttribute("patientDtoList",patientDtoList);
            return "doctor-account.html";
        }
        else {
            return "doctor-invalid-credentials.html";
        }
    }


}
