package healthcare_management_system.test_application.service;


import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import healthcare_management_system.test_application.model.Appointment;
import healthcare_management_system.test_application.model.Doctor;
import healthcare_management_system.test_application.model.Patients;
import healthcare_management_system.test_application.model.Prescription;
import healthcare_management_system.test_application.repository.PrescriptionRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import static com.lowagie.text.FontFactory.*;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    public void save(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    public void sendPrescription(Prescription prescription,
                                 Patients patients,
                                 Doctor doctor,
                                 Appointment appointment){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("darshanva05@gmail.com");

        message.setTo(patients.getEmail());
        message.setText("Dear "+patients.getFirstName()+" "+patients.getLastName()+",\n" +
                "\n" +
                "We hope this message finds you well. Following your recent appointment on "+appointment.getDate()+", I am writing to provide you with the details of your prescription.\n" +
                "\n\n" +
                "Medication: \n"+
                prescription.getMedication()+

                "\n\nPlease follow the instructions provided, and feel free to reach out if you have any questions or concerns regarding your medication or treatment plan.\n" +
                "\n" +
                "Take care, and I look forward to seeing you at your next appointment.\n" +
                "\n" +
                "Best regards,\nDr. "+ doctor.getFirstName()+" "+doctor.getLastName()+".\n"+
                "("+doctor.getMedicalExpertise()+"), "+doctor.getQualification()+".\n"
        );
        message.setSubject("Prescription for Your Recent Appointment with Dr."+doctor.getFirstName()+" "+doctor.getLastName()+".");
        javaMailSender.send(message);
    }


    public Prescription getByID(int prescriptonId) {

        Optional<Prescription> optional = prescriptionRepository.findById(prescriptonId);
        Prescription prescription = null;
        if (optional.isPresent()) {
            prescription = optional.get();
        } else {
            throw new RuntimeException(" Account Holder not found for id");
        }
        return prescription;


    }

    public ByteArrayInputStream download(Prescription prescription1) {

        String medication = prescription1.getMedication();
        String notes = prescription1.getNotes();
        String pulse = prescription1.getPulse();
        String diagnosis = prescription1.getDiagnosis();
        String  bp = prescription1.getBp();
        int weight = prescription1.getWeight();
        String spo2 = prescription1.getSpo2();

        Doctor doctor = doctorService.password(prescription1.getDoctorId());
        String doctorFirstName =  doctor.getFirstName();
        String doctorLastName = doctor.getLastName();

        Patients patients = patientService.password(prescription1.getPatientId());
        String patientFirstName = patients.getFirstName();
        String patientLastName = patients.getLastName();
        int age = patients.getAge();
        int patientId = patients.getPatientId();



        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,outputStream);
        document.open();
        Font font = Font.getFont(HELVETICA_BOLD);
        Paragraph title = new Paragraph("CommunityCare Diagnostics");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph patientDetails = new Paragraph("Doctor: "+doctorFirstName+" "+doctorLastName+"\n"+
                                                    "Patient name: "+patientFirstName+" "+patientLastName+"\n"+
                                                    "Age: "+age+" years\n\n" +
                                                    "weight: "+weight+"\n" +
                                                    "Bp: "+bp+"\n" +
                                                    "spO2: "+spo2+"\n" +
                                                    "Pulse: "+pulse+"\n\n" +
                                                    "Medication: "+medication+"\n" +
                                                    "Notes: "+notes+"\n\n" );
        patientDetails.setAlignment(Element.ALIGN_LEFT);
        document.add(patientDetails);
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
