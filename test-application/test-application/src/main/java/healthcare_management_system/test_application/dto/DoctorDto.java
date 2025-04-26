package healthcare_management_system.test_application.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class DoctorDto implements Serializable {


    private @Getter @Setter int doctorId;

    private @Getter @Setter String firstName;

    private @Getter @Setter String lastName;

    private @Getter @Setter int experience;

    private @Getter @Setter String medicalExpertise;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getMedicalExpertise() {
        return medicalExpertise;
    }

    public void setMedicalExpertise(String medicalExpertise) {
        this.medicalExpertise = medicalExpertise;
    }
}
