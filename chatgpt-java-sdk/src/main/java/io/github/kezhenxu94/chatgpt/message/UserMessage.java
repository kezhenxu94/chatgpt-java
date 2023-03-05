package io.github.kezhenxu94.chatgpt.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final class UserMessage implements Message {
  private final String content;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public UserMessage(@JsonProperty("content") String content) {
    this.content = content;
  }

  @Override
  public Role role() {
    return Role.USER;
  }

  @Override
  public String content() {
    return content;
  }
}
