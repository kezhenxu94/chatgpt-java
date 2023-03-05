package io.github.kezhenxu94.chatgpt;

class ChatGPTBuilder implements ChatGPT.Builder {
  private String apiKey;
  private int conversationSize = Integer.MAX_VALUE;

  @Override
  public ChatGPT build() {
    if (apiKey == null || apiKey.isEmpty()) {
      apiKey = System.getenv("CHATGPT_API_KEY");
    }

    if (apiKey == null || apiKey.isEmpty()) {
      throw new IllegalArgumentException("API key is required");
    }

    return new ChatGPTImpl(apiKey, conversationSize);
  }

  public ChatGPTBuilder conversationSize(int conversationSize) {
    this.conversationSize = conversationSize;
    return this;
  }

  @Override
  public ChatGPT.Builder apiKey(String apiKey) {
    this.apiKey = apiKey;
    return this;
  }
}
