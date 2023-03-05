package io.github.kezhenxu94.chatgpt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kezhenxu94.chatgpt.internal.ChatGPTHttpRequest;
import io.github.kezhenxu94.chatgpt.internal.ChatGPTHttpResponse;
import io.github.kezhenxu94.chatgpt.internal.JsonBodyHandler;
import io.github.kezhenxu94.chatgpt.message.Message;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

final class ConversationImpl implements Conversation {
  private static final String API_URL = "https://api.openai.com/v1/chat/completions";

  private final ChatGPT chatGPT;
  private final List<Message> messages;
  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  ConversationImpl(ChatGPT chatGPT) {
    this.chatGPT = chatGPT;
    this.messages = new ArrayList<>();
    this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    this.objectMapper = new ObjectMapper();
  }

  ConversationImpl(ChatGPT chatGPT, String system) {
    this(chatGPT);
    messages.add(Message.ofSystem(system));
  }

  @Override
  public Message ask(String question) throws IOException, InterruptedException {
    messages.add(Message.ofUser(question));

    final var request = ChatGPTHttpRequest.builder().messages(messages).build();
    final var response =
        httpClient.send(
            HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + chatGPT.apiKey())
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(request)))
                .build(),
            new JsonBodyHandler<>(ChatGPTHttpResponse.class));
    final var chatCompletionResponse = response.body();
    final var choice = chatCompletionResponse.choices().iterator().next();

    messages.add(choice.message());

    return choice.message();
  }
}
