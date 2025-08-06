package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.CoagulationProfileTest;
import healthcare_management_system.test_application.model.CompleteBloodCountTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CbctRepository extends JpaRepository<CompleteBloodCountTest, Integer> {

    List<CompleteBloodCountTest> findByStatus(String status);

}
