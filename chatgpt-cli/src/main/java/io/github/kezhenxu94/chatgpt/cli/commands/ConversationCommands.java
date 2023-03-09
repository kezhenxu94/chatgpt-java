package io.github.kezhenxu94.chatgpt.cli.commands;

import io.github.kezhenxu94.chatgpt.ChatGPT;
import io.github.kezhenxu94.chatgpt.Conversation;
import io.github.kezhenxu94.chatgpt.message.Message;
import io.github.kezhenxu94.chatgpt.message.Role;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class ConversationCommands {
  private final ChatGPT chatGPT;

  private Conversation conversation;

  public ConversationCommands(ChatGPT chatGPT) {
    this.chatGPT = chatGPT;
  }

  @ShellMethod(
      "Set the system message as an instruction to guide the ChatGPT in following conversations")
  @ShellMethodAvailability("isConversationNull")
  public String system(String system) {
    if (conversation == null) {
      conversation = chatGPT.newConversation(system);
    } else if (conversation.messages().stream().allMatch(it -> it.role() != Role.SYSTEM)) {
      conversation.messages().add(0, Message.ofSystem(system));
    }
    return "System message set to: " + system;
  }

  @ShellMethod(
      "Load a conversation by ID, if there is no conversation with the ID, a new one will be created with the ID")
  public String load(String id) throws Exception {
    conversation = chatGPT.loadConversation(id);
    return "You are now at conversation: " + id;
  }

  @ShellMethod("Ask a question in the current conversation")
  public String ask(String question) throws Exception {
    if (conversation == null) {
      conversation = chatGPT.newConversation();
    }

    final var answer = conversation.ask(question);
    return answer.content().trim();
  }

  Availability isConversationNull() {
    return conversation == null
            || conversation.messages().stream().allMatch(it -> it.role() != Role.SYSTEM)
        ? Availability.available()
        : Availability.unavailable(
            "you are already in a conversation, system message can only be set in new conversation.");
  }
}
