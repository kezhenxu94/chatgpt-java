package io.github.kezhenxu94.chatgpt;

import java.nio.file.Path;
import java.nio.file.Paths;

class ChatGPTBuilder implements ChatGPT.Builder {
  private String apiKey;
  private int conversationSize = Integer.MAX_VALUE;
  private Path dataPath = Paths.get(System.getProperty("user.home"), ".chatgpt", "data");

  @Override
  public ChatGPT build() {
    if (apiKey == null || apiKey.isEmpty()) {
      apiKey = System.getenv("CHATGPT_API_KEY");
    }

    if (apiKey == null || apiKey.isEmpty()) {
      throw new IllegalArgumentException("API key is required");
    }

    return new ChatGPTImpl(apiKey, conversationSize, dataPath);
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

  @Override
  public ChatGPT.Builder dataPath(String dataPath) {
    this.dataPath = Paths.get(dataPath);
    return this;
  }

  @Override
  public ChatGPT.Builder dataPath(Path dataPath) {
    this.dataPath = dataPath;
    return this;
  }
}
