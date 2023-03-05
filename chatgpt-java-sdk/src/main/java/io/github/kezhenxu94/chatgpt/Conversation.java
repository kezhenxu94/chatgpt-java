package io.github.kezhenxu94.chatgpt;

import io.github.kezhenxu94.chatgpt.message.Message;

import java.io.IOException;

public interface Conversation {
  Message ask(String question) throws IOException, InterruptedException;
}
