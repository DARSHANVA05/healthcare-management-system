package healthcare_management_system.test_application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Entity
@Table(name = "Ai")
public class Ai {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int id;
    public String medicine;

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
