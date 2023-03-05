package io.github.kezhenxu94.chatgpt.cli;

import io.github.kezhenxu94.chatgpt.ChatGPT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatgptCliApplication {
  public static void main(String[] args) {
    SpringApplication.run(ChatgptCliApplication.class, args);
  }

  @Bean
  ChatGPT chatGPT() {
    return ChatGPT.builder().build();
  }
}
