package io.github.kezhenxu94.chatgpt;

import java.io.IOException;
import java.nio.file.Path;

public interface ChatGPT {
  public static final String API_URL = "https://api.openai.com/v1/chat/completions";

  Conversation newConversation();

  Conversation newConversation(String system);

  Conversation newConversationWithID(String id);

  Conversation loadConversation(String id) throws IOException;

  void removeConversation(Conversation conversation);

  String apiKey();

  int conversationSize();

  Path dataPath();

  static Builder builder() {
    return new ChatGPTBuilder();
  }

  interface Builder {
    Builder conversationSize(int conversationSize);

    Builder apiKey(String apiKey);

    Builder dataPath(String persistentPath);

    Builder dataPath(Path persistentPath);

    ChatGPT build();
  }
}
