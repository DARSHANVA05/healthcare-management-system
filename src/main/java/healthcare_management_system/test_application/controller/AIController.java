package healthcare_management_system.test_application.controller;
import healthcare_management_system.test_application.model.Ai;
import healthcare_management_system.test_application.model.Patients;
import healthcare_management_system.test_application.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/ai")
public class AIController {

    @Autowired
    AIService aiService;

    @PostMapping("/ask")
    public String getResponse(@ModelAttribute("Ai") Ai ai,
                              Model model) {
        String answer = aiService.getSummarizeText(ai.getMedicine());
        model.addAttribute("medicine",ai.getMedicine());
        model.addAttribute("answer",answer.substring(13).replace("}", ""));
        return "ai/medicine-composition.html";
    }

}
