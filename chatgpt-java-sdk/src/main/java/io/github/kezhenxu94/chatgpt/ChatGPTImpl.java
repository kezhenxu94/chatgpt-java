package io.github.kezhenxu94.chatgpt;

import io.github.kezhenxu94.chatgpt.message.Message;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

final class ChatGPTImpl implements ChatGPT {
  private final List<Conversation> conversations;

  private final String apiKey;
  private final int conversationSize;
  private final Path dataPath;

  public ChatGPTImpl(String apiKey, int conversationSize, Path dataPath) {
    this.apiKey = apiKey;
    this.conversationSize = conversationSize;
    this.dataPath = dataPath;

    this.conversations = new ArrayList<>();

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  try {
                    save();
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                }));
  }

  @Override
  public Conversation newConversation() {
    final var newConversation = new ConversationImpl(this);
    conversations.add(newConversation);
    return newConversation;
  }

  @Override
  public Conversation newConversation(String system) {
    final var newConversation = new ConversationImpl(this, Message.ofSystem(system));
    conversations.add(newConversation);
    return newConversation;
  }

  @Override
  public Conversation newConversationWithID(String id) {
    final var conversation = new ConversationImpl(this, id);
    conversations.add(conversation);
    return conversation;
  }

  @Override
  public Conversation loadConversation(String id) throws IOException {
    final var conversation = new ConversationImpl(this, id);
    conversations.add(conversation);

    conversation.load();
    return conversation;
  }

  @Override
  public void removeConversation(Conversation conversation) {
    conversations.remove(conversation);
  }

  @Override
  public String apiKey() {
    return apiKey;
  }

  @Override
  public int conversationSize() {
    return conversationSize;
  }

  @Override
  public Path dataPath() {
    return dataPath;
  }

  void save() throws IOException {
    for (final var conversation : conversations) {
      conversation.save();
    }
  }
}
