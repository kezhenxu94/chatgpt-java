package io.github.kezhenxu94.chatgpt.cli;

import io.github.kezhenxu94.chatgpt.ChatGPT;
import io.github.kezhenxu94.chatgpt.internal.ChatGPTHttpRequest;
import io.github.kezhenxu94.chatgpt.internal.ChatGPTHttpResponse;
import io.github.kezhenxu94.chatgpt.message.AssistantMessage;
import io.github.kezhenxu94.chatgpt.message.Message;
import io.github.kezhenxu94.chatgpt.message.Role;
import io.github.kezhenxu94.chatgpt.message.SystemMessage;
import io.github.kezhenxu94.chatgpt.message.UserMessage;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@RegisterReflectionForBinding({
  ChatGPTHttpRequest.class,
  ChatGPTHttpResponse.class,
  ChatGPTHttpResponse.Usage.class,
  ChatGPTHttpResponse.Choice.class,
  Message.class,
  AssistantMessage.class,
  SystemMessage.class,
  UserMessage.class,
  Role.class
})
@ImportRuntimeHints({CLIRuntimeHints.class})
public class ChatGPTCLIApplication {
  public static void main(String[] args) {
    SpringApplication.run(ChatGPTCLIApplication.class, args);
  }

  @Bean
  ChatGPT chatGPT() {
    return ChatGPT.builder().build();
  }
}
