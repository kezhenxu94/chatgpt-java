package io.github.kezhenxu94.chatgpt.cli.commands;

import io.github.kezhenxu94.chatgpt.ChatGPT;
import io.github.kezhenxu94.chatgpt.Conversation;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ConversationCommands {
  private final ChatGPT chatGPT;

  private Conversation conversation;

  public ConversationCommands(ChatGPT chatGPT) {
    this.chatGPT = chatGPT;
  }

  @ShellMethod("Load a conversation by ID")
  public String load(String id) throws Exception {
    conversation = chatGPT.loadConversation(id);
    return "You are now at conversation: " + id;
  }

  @ShellMethod("Ask a question")
  public String ask(String question) throws Exception {
    if (conversation == null) {
      conversation = chatGPT.newConversation();
    }

    final var answer = conversation.ask(question);
    return answer.content().trim();
  }
}
