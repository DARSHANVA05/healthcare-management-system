package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.Appointment;
import healthcare_management_system.test_application.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDoctorId(int doctorId);
    List<Appointment> findByDate(String date);
}
