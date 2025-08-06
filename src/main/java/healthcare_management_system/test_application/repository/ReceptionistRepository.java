package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer> {

}
