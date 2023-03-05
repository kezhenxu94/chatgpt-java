package io.github.kezhenxu94.chatgpt;

public interface ChatGPT {
  Conversation newConversation();

  Conversation newConversation(String system);

  String apiKey();

  static Builder builder() {
    return new ChatGPTBuilder();
  }

  interface Builder {
    Builder conversationSize(int conversationSize);

    Builder apiKey(String apiKey);

    ChatGPT build();
  }
}
