package io.github.kezhenxu94.chatgpt.cli;

import io.github.kezhenxu94.chatgpt.ChatGPT;
import io.github.kezhenxu94.chatgpt.internal.ChatGPTHttpRequest;
import io.github.kezhenxu94.chatgpt.internal.ChatGPTHttpResponse;
import io.github.kezhenxu94.chatgpt.message.Message;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RegisterReflectionForBinding({ChatGPTHttpRequest.class, ChatGPTHttpResponse.class, Message.class})
public class ChatgptCliApplication {
  public static void main(String[] args) {
    SpringApplication.run(ChatgptCliApplication.class, args);
  }

  @Bean
  ChatGPT chatGPT() {
    return ChatGPT.builder().build();
  }
}
