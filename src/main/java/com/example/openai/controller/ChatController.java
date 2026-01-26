package com.example.openai.controller;


import com.example.openai.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    //LLM(gpt-4o)과 통신할 수 있는 객체: ChatClient
    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatService.chat(message);
    }

    @GetMapping("/chatmessage")
    public String chatmessage(String message) {
        return chatService.chatmessage(message);
    }

    @GetMapping("/chatplace")
    public String chatplace(String subject, String tone,  String message) {
        return chatService.chatplace(subject, tone, message);
    }
}
