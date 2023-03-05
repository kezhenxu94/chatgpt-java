package io.github.kezhenxu94.chatgpt.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final class SystemMessage implements Message {
  private final String content;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public SystemMessage(@JsonProperty("content") String content) {
    this.content = content;
  }

  @Override
  public Role role() {
    return Role.SYSTEM;
  }

  @Override
  public String content() {
    return content;
  }
}
