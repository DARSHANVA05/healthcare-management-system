package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.LabTechnician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<LabTechnician, Integer> {
}
