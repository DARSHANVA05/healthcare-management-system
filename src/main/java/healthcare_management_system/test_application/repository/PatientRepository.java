package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patients,Integer> {

}
