package io.github.kezhenxu94.chatgpt.cli.commands;

import io.github.kezhenxu94.chatgpt.ChatGPT;
import io.github.kezhenxu94.chatgpt.Conversation;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ConversationCommands {
  private final Conversation conversation;

  public ConversationCommands(ChatGPT chatGPT) {
    conversation = chatGPT.newConversation();
  }

  @ShellMethod(value = "Ask a question")
  public String ask(String question) throws Exception {
    final var answer = conversation.ask(question);
    return answer.content().trim();
  }
}
