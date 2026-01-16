package main.java.com.example.openai.controller;


import main.java.com.example.openai.service.ChatService;

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
}
