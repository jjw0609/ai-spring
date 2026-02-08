package com.example.openai.config;

import com.example.openai.service.ChatService;
import org.springframework.boot.CommandLineRunner;

public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final ChatService chatService;

    public CommandLineAppStartupRunner(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void run(String... args) throws Exception {
        chatService.startChat();
    }
}
