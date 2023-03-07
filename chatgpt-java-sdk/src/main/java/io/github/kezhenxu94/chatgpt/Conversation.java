package io.github.kezhenxu94.chatgpt;

import io.github.kezhenxu94.chatgpt.message.Message;

import java.io.IOException;import java.util.List;

public interface Conversation {
  Message ask(String question) throws IOException, InterruptedException;

  String id();

  List<Message> messages();

  void save() throws IOException;

  void load() throws IOException;
}
