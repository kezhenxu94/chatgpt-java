package io.github.kezhenxu94.chatgpt;

final class ChatGPTImpl implements ChatGPT {
  private final String apiKey;
  private final int conversationSize;

  public ChatGPTImpl(String apiKey, int conversationSize) {
    this.apiKey = apiKey;
    this.conversationSize = conversationSize;
  }

  @Override
  public Conversation newConversation() {
    return new ConversationImpl(this);
  }

  @Override
  public Conversation newConversation(String system) {
    return new ConversationImpl(this, system);
  }

  @Override
  public String apiKey() {
    return apiKey;
  }
}
