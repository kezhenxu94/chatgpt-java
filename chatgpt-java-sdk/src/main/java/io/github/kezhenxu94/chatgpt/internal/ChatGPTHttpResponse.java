package io.github.kezhenxu94.chatgpt.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.kezhenxu94.chatgpt.message.Message;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatGPTHttpResponse {
  private final String id;
  private final String object;
  private final long created;
  private final String model;
  private final Usage usage;
  private final List<Choice> choices;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public ChatGPTHttpResponse(
      @JsonProperty("id") String id,
      @JsonProperty("object") String object,
      @JsonProperty("created") long created,
      @JsonProperty("model") String model,
      @JsonProperty("usage") Usage usage,
      @JsonProperty("choices") List<Choice> choices) {
    this.id = id;
    this.object = object;
    this.created = created;
    this.model = model;
    this.usage = usage;
    this.choices = choices;
  }

  public String id() {
    return id;
  }

  public String object() {
    return object;
  }

  public long created() {
    return created;
  }

  public String model() {
    return model;
  }

  public Usage usage() {
    return usage;
  }

  public List<Choice> choices() {
    return choices;
  }

  public static class Usage {
    private final int promptTokens;
    private final int completionTokens;
    private final int totalTokens;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Usage(
        @JsonProperty("prompt_tokens") int promptTokens,
        @JsonProperty("completion_tokens") int completionTokens,
        @JsonProperty("total_tokens") int totalTokens) {
      this.promptTokens = promptTokens;
      this.completionTokens = completionTokens;
      this.totalTokens = totalTokens;
    }

    public int promptTokens() {
      return promptTokens;
    }

    public int completionTokens() {
      return completionTokens;
    }

    public int totalTokens() {
      return totalTokens;
    }
  }

  public static class Choice {
    private final Message message;
    private final String finishReason;
    private final int index;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    Choice(
        @JsonProperty("message") Message message,
        @JsonProperty("finish_reason") String finishReason,
        @JsonProperty("index") int index) {
      this.message = message;
      this.finishReason = finishReason;
      this.index = index;
    }

    public Message message() {
      return message;
    }

    public String finishReason() {
      return finishReason;
    }

    public int index() {
      return index;
    }
  }
}
