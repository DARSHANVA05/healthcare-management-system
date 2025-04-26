package healthcare_management_system.test_application.service;

import healthcare_management_system.test_application.model.Appointment;
import healthcare_management_system.test_application.model.Doctor;
import healthcare_management_system.test_application.model.Patients;
import healthcare_management_system.test_application.repository.AppointmentRepository;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
        System.out.println("Appointment saved");
    }

    public void sendMail(Patients patients, Doctor doctor,Appointment appointment) throws UnknownHostException, MailSendException, MailConnectException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("darshanva05@gmail.com");
        //message.setFrom("darshanva05work@gmail.com");

        message.setTo(patients.getEmail());
        message.setText("Dear "+patients.getFirstName()+" "+patients.getLastName()+",\n" +
                "\n" +
                "We are writing to confirm your appointment with Dr. "+doctor.getFirstName()+" "+doctor.getLastName()+" on "+appointment.getDate()+" at "+appointment.getTime()+".\n" +
                "\n" +
                "We kindly request that you arrive few minutes early. If you have any questions or need to reschedule your appointment, please feel free to contact us at 7892027335.\n" +
                "\n" +
                "Thank you for choosing CommunityCare.\n" +
                "\n" +
                "Sincerely,\n" +
                "CommunityCare Diagnostics\n" +
                "7892027335");
        message.setSubject("Confirmation of Your Appointment with Dr."+doctor.getFirstName()+" "+doctor.getLastName()+" on "+appointment.getDate());
        javaMailSender.send(message);

    }

    public Appointment findAppointment(int appointmentId) {

        Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
        Appointment appointment = null;
        if (optional.isPresent()) {
            appointment = optional.get();
        } else {
            throw new RuntimeException("Account Holder not found for id: "+appointmentId);
        }
        return appointment;

    }

    public void delete(int appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
