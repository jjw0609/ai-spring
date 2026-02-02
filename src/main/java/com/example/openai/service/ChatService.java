package com.example.openai.service;

import com.example.openai.entity.Answer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ChatResponse chatjson(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();    //ChatResponse(?) --> JSON
    }

    public Answer chatobject(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(Answer.class);
    }

    private final String recipeTemplate = """
            Answer for {foodName} for {question}?
            """;

    public Answer recipe(String foodName, String question) {
        return chatClient.prompt()
                .user(userSpec -> userSpec.text(recipeTemplate)
                        .param("foodName", foodName)
                        .param("question", question)
                )
                .call()
                .entity(Answer.class);
    }

    public List<String> chatlist(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
    }
}
