package healthcare_management_system.test_application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private @Getter
    @Setter int doctorId;
    @Column(nullable = false)
    private @Getter @Setter String firstName;
    @Column(nullable = false)
    private @Getter @Setter String lastName;
    @Column(nullable = false)
    private @Getter @Setter int age;
    @Column(nullable = false)
    private @Getter @Setter String phoneNo;
    @Column
    private @Getter @Setter String email;
    @Column(nullable = false)
    private @Getter @Setter String gender;
    @Column(nullable = false)
    private @Getter @Setter String dob;
    @Column(nullable = false)
    private @Getter @Setter String password;
    @Column(nullable = false)
    private @Getter @Setter String medicalExpertise;
    @Column(nullable = false)
    private @Getter @Setter int experience;
    @Column(nullable = false)
    private @Getter @Setter String qualification ;
    @Column(nullable = false)
    private @Getter @Setter int otp;

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMedicalExpertise() {
        return medicalExpertise;
    }

    public void setMedicalExpertise(String medicalExpertise) {
        this.medicalExpertise = medicalExpertise;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

}
