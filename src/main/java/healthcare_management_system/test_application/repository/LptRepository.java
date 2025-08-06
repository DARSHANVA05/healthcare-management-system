package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.CoagulationProfileTest;
import healthcare_management_system.test_application.model.LipidProfileTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LptRepository extends JpaRepository<LipidProfileTest, Integer> {
    List<LipidProfileTest> findByStatus(String status);
}
