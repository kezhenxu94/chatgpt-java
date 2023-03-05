package io.github.kezhenxu94.chatgpt.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
  @JsonProperty("user")
  USER,

  @JsonProperty("assistant")
  ASSISTANT,

  @JsonProperty("system")
  SYSTEM
}
