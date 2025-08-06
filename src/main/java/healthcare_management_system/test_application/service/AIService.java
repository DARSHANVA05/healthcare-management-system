package healthcare_management_system.test_application.service;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;
import java.util.List;

@Service
public class AIService {

    @Autowired
    OllamaChatModel chatModel;

    // generate response in JSON format
//    public String getResponse(String category, String year) {
//        String template = """
//                 Please provide me best book for the given {category} and the {year}.
//                 Please do provide a summary of the book as well, the information should be\s
//                 limited and not much in depth. The response should be in the JSON format
//                 containing this information:
//                 category, book, year, review, author, summary
//                """;
//
//        PromptTemplate promptTemplate = new PromptTemplate(template);
//        promptTemplate.add("category", category);
//        promptTemplate.add("year", year);
//
//        Prompt prompt = promptTemplate.create();
//
//        return chatModel.call(prompt).getResult().getOutput().getContent();
//    }


    // summarize text in the structured format
    public String getSummarizeText(String text){
        String template = """
                 You will be given an {medicineName}.
                 You need to find medicine composition and provide a summary of the composition in mg,and the information should be
                 in depth and in a paragraph.Provide the output in the JSON format with these keys : summary.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        promptTemplate.add("medicineName", text);

        Prompt prompt = promptTemplate.create();

        return chatModel.call(prompt).getResult().getOutput().getContent();
    }

}
