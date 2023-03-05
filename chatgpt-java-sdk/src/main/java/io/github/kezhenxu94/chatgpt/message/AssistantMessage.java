package io.github.kezhenxu94.chatgpt.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class AssistantMessage implements Message {
  private final String content;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public AssistantMessage(@JsonProperty("content") String content) {
    this.content = content;
  }

  @Override
  public Role role() {
    return Role.ASSISTANT;
  }

  @Override
  public String content() {
    return content;
  }
}
