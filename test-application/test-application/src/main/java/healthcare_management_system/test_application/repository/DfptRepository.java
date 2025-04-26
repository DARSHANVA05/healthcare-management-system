package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.CoagulationProfileTest;
import healthcare_management_system.test_application.model.DengueFeverPanelTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DfptRepository extends JpaRepository<DengueFeverPanelTest, Integer> {
    List<DengueFeverPanelTest> findByStatus(String status);

}
