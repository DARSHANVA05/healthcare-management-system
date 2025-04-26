package healthcare_management_system.test_application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "labTechnician")
public class LabTechnician {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private @Getter
    @Setter int labTechnicianId;
    @Column(nullable = false)
    private @Getter @Setter String name;
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

    public int getLabTechnicianId() {
        return labTechnicianId;
    }

    public void setLabTechnicianId(int labTechnicianId) {
        this.labTechnicianId = labTechnicianId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
