package main.java.com.example.openai.config;


@Configuration
public class AppConfig {

    //ChatClient <---- openAI key  ----> LLM(openai)
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {

        return chatClientBuilder.build();
    }
}
