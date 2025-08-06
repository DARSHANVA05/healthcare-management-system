package healthcare_management_system.test_application.service;

import healthcare_management_system.test_application.model.Doctor;
import healthcare_management_system.test_application.model.Patients;
import healthcare_management_system.test_application.repository.DoctorRepository;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    public void otpValidation(Doctor doctor, int tempOtp) throws UnknownHostException, MailSendException, MailConnectException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("darshanva05@gmail.com");
        //message.setFrom("darshanva05work@gmail.com");

        message.setTo(doctor.getEmail());
        message.setText("Dear "+doctor.getFirstName()+" "+doctor.getLastName()+",\n" +
                "\n" +
                "We request you to verify your account with CommunityCare Diagnostics. To proceed, please use the One-Time Password (OTP) below:\n" +
                "\n" +
                "OTP:"+tempOtp+"\n" +
                "\n" +
                "Please enter it on our website to complete the verification process. For security reasons, this code should not be shared with anyone.\n" +
                "\n" +
                "If you did not request this verification or believe this email was sent to you by mistake, please ignore it. If you have any concerns or need assistance, please free to contact our support team at 7892027335.\n" +
                "\n" +
                "Thank you for your prompt attention to this matter.\n" +
                "\n" +
                "Best regards,\n CommunityCare Diagnostics");
        message.setSubject("Your OTP for Account creation");
        javaMailSender.send(message);

    }

    public void save(Doctor tempAccount) {
        doctorRepository.save(tempAccount);
    }

    public Doctor password(int userInp) {

        Optional<Doctor> optional = doctorRepository.findById(userInp);
        Doctor doctor = null;
        if (optional.isPresent()) {
            doctor = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return doctor;

    }
}
