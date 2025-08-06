package healthcare_management_system.test_application.service;

import healthcare_management_system.test_application.model.Patients;
import healthcare_management_system.test_application.repository.PatientRepository;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.net.UnknownHostException;

import java.sql.*;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    PatientRepository patientRepository;

    public int save(Patients patients) {
        patientRepository.save(patients);
        return patients.getPatientId();
    }

    public void otpValidation(int patientId, Patients patients, int otp) throws UnknownHostException, MailSendException, MailConnectException
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("darshanva05@gmail.com");
        //message.setFrom("darshanva05work@gmail.com");

        message.setTo(patients.getEmail());
        message.setText("Dear "+patients.getFirstName()+" "+patients.getLastName()+",\n" +
                "\n" +
                "We request you to verify your account with CommunityCare Diagnostics. To proceed, please use the One-Time Password (OTP) below:\n" +
                "\n" +
                "OTP:"+otp+"\n" +
                "\n" +
                "Please enter it on our website to complete the verification process. For security reasons, this code should not be shared with anyone.\n" +
                "\n" +
                "If you did not request this verification or believe this email was sent to you by mistake, please ignore it. If you have any concerns or need assistance, feel free to contact our support team at 7892027335.\n" +
                "\n" +
                "Thank you for your prompt attention to this matter.\n" +
                "\n" +
                "Best regards,\nCommunityCare Diagnostics");
        message.setSubject("Your OTP for Account creation");
        javaMailSender.send(message);

    }

    public void confirmation(int patientId, Patients patients)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("darshanva05@gmail.com");
        //message.setFrom("darshanva05work@gmail.com");

        message.setTo(patients.getEmail());
        message.setText("Dear "+patients.getFirstName()+" "+patients.getLastName()+",\n" +
                "\n" +
                "We’re so glad to have you join the CommunityCare Diagnostics family!\n" +
                "Your health and well-being are at the heart of everything we do, and it’s our privilege to be part of your journey.\n" +
                "Your account has been successfully created, giving you access to personalized tools and resources to support your healthcare needs.\n\nYour credentials\n\n" +
                "\tPatient ID: "+patientId+"\n" +
                "\tPassword: "+patients.getPassword()+"\n" +
                "\n" +
                "If you ever have questions or need assistance, our support team is just a call or email away. " +
                "Contact us at 7892027335 we’re here to help, every step of the way." +
                "Thank you for trusting us with your care. \n\nTogether, we’ll work towards a healthier tomorrow.\n\nSincerely,\nCommunityCare Diagnostics Team");
        message.setSubject("Account successfully created!");
        javaMailSender.send(message);

    }

    public Patients password(int patientId) {

        Optional<Patients> optional = patientRepository.findById(patientId);
        Patients patients = null;
        if (optional.isPresent()) {
            patients = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return patients;


    }
}
