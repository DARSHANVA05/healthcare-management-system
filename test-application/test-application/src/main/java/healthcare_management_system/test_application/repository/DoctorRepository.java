package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    List<Doctor>findByMedicalExpertise(String medicalExpertise);

}
