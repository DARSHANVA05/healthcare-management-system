package healthcare_management_system.test_application.service;

import healthcare_management_system.test_application.model.Receptionist;
import healthcare_management_system.test_application.repository.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReceptionistService {
    @Autowired
    ReceptionistRepository receptionistRepository;

    public Receptionist password(int receptionistId) {

        Optional<Receptionist> optional = receptionistRepository.findById(receptionistId);
        Receptionist receptionist = null;
        if (optional.isPresent()) {
            receptionist = optional.get();
        } else {
            throw new RuntimeException(" Receptionist not found for id");
        }
        return receptionist;

    }
}
