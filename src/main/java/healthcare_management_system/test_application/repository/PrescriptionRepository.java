package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Integer> {
    List<Prescription> findByPatientId(int patientId);
}
