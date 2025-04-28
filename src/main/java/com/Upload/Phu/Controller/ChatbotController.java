package com.Upload.Phu.Controller;

import com.Upload.Phu.Service.ChatbotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Chatbot")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    public static class MessageRequest {
        public String user_input;
    }

    @PostMapping("/message")
    public String sendMessage(@RequestBody MessageRequest request) {
        return chatbotService.getChatbotResponse(request.user_input);
    }
}
