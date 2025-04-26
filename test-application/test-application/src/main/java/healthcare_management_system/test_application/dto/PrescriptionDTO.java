package healthcare_management_system.test_application.dto;

import healthcare_management_system.test_application.model.Appointment;
import healthcare_management_system.test_application.model.Doctor;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

public class PrescriptionDTO {

    private String date;
    private String firstName;
    private String lastName;
    private String specialisation;

    private int prescriptionId;

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}
