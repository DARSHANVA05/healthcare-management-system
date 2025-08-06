package healthcare_management_system.test_application.repository;

import healthcare_management_system.test_application.model.CoagulationProfileTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface CptRepository extends JpaRepository<CoagulationProfileTest, Integer> {

    List<CoagulationProfileTest> findByStatus(String status);

}
