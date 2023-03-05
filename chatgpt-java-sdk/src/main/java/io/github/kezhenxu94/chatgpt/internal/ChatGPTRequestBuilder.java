package io.github.kezhenxu94.chatgpt.internal;

import io.github.kezhenxu94.chatgpt.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatGPTRequestBuilder {
  private List<Message> messages = new ArrayList<>();
  private double temperature = 1;
  private double topP = 1;
  private int n = 1;
  private boolean stream = false;
  private Integer maxTokens;
  private double presencePenalty = 0;
  private double frequencyPenalty = 0;
  private Map<String, String> logitBias;
  private String user;

  public ChatGPTRequestBuilder messages(List<Message> messages) {
    this.messages = messages;
    return this;
  }

  public ChatGPTRequestBuilder temperature(double temperature) {
    this.temperature = temperature;
    return this;
  }

  public ChatGPTRequestBuilder topP(double topP) {
    this.topP = topP;
    return this;
  }

  public ChatGPTRequestBuilder n(int n) {
    this.n = n;
    return this;
  }

  public ChatGPTRequestBuilder stream(boolean stream) {
    this.stream = stream;
    return this;
  }

  public ChatGPTRequestBuilder maxTokens(int maxTokens) {
    this.maxTokens = maxTokens;
    return this;
  }

  public ChatGPTRequestBuilder presencePenalty(double presencePenalty) {
    this.presencePenalty = presencePenalty;
    return this;
  }

  public ChatGPTRequestBuilder frequencyPenalty(double frequencyPenalty) {
    this.frequencyPenalty = frequencyPenalty;
    return this;
  }

  public ChatGPTRequestBuilder logitBias(Map<String, String> logitBias) {
    this.logitBias = logitBias;
    return this;
  }

  public ChatGPTRequestBuilder user(String user) {
    this.user = user;
    return this;
  }

  public ChatGPTHttpRequest build() {
    return new ChatGPTHttpRequest(
        messages,
        temperature,
        topP,
        n,
        stream,
        maxTokens,
        presencePenalty,
        frequencyPenalty,
        logitBias,
        user);
  }
}
