package io.github.kezhenxu94.chatgpt;

import java.io.IOException;import java.nio.file.Files;

public class ChatGPTTest {
  public static void main(String[] args) throws IOException, InterruptedException {
    final var chatGPT = ChatGPT
            .builder()
            .dataPath(Files.createTempDirectory("chatgpt")) // Persist the chat history to a data path
            .build();

    // Start a new conversation
    final var conversation = chatGPT.newConversation();
    System.out.println(conversation.ask("What's your name?").content());
    // Output: I'm an AI language model developed by OpenAI, and I don't have a name. What can I help you with today?
    System.out.println(conversation.ask("What did I ask you?").content());
    // Output: You asked for my name.
    conversation.save(); // Save the history manually, conversations are saved on shutdown by default.

    final var conversation2 = chatGPT.newConversation("You are a software engineer.");
    System.out.println(conversation2.ask("What's your job?").content());
    System.out.println(conversation2.ask("What's your day to day work?").content());

    // Load a conversation by the ID
    final var conversation3 = chatGPT.loadConversation(conversation.id());
    conversation3.ask("What did I ask you?");
    // Should print the same as the first conversation
  }
}
