package io.github.kezhenxu94.chatgpt.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "role")
@JsonSubTypes({
  @JsonSubTypes.Type(value = UserMessage.class, name = "user"),
  @JsonSubTypes.Type(value = SystemMessage.class, name = "system"),
  @JsonSubTypes.Type(value = AssistantMessage.class, name = "assistant")
})
public interface Message {
  @JsonProperty
  Role role();

  @JsonProperty
  String content();

  static Message ofUser(String content) {
    return new UserMessage(content);
  }

  static Message ofSystem(String content) {
    return new SystemMessage(content);
  }
}
