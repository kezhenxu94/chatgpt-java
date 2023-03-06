package io.github.kezhenxu94.chatgpt;

import java.io.IOException;

public class ChatGPTTest {
  public static void main(String[] args) throws IOException, InterruptedException {
    final var chatGPT = ChatGPT.builder().build();
    final var conversation = chatGPT.newConversation();
    System.out.println(conversation.ask("What's your name?").content());
    System.out.println(conversation.ask("What did I ask you?").content());

    final var conversation2 = chatGPT.newConversation("You are a software engineer.");
    System.out.println(conversation2.ask("What's your job?").content());
    System.out.println(conversation2.ask("What's your day to day work?").content());
  }
}
