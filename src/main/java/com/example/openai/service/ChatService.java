package com.example.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message) {
        return chatClient.prompt()  //프롬프트 생성
                .user(message)  //사용자 메세지
                .call()     //호출
                .content(); //ChatResponse --> 요청 정보를 받는 부분(문자열)
    }

    public String chatmessage(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse() //ChatResponse
                .getResult()
                .getOutput()
                .getText();
    }

    public String chatplace(String subject, String tone, String message) {
        return chatClient.prompt()
                .user(message)
                .system(sp -> sp
                        .param("subject", subject)
                        .param("tone", tone)
                )
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }
}
