package io.github.kezhenxu94.chatgpt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kezhenxu94.chatgpt.internal.ChatGPTHttpRequest;
import io.github.kezhenxu94.chatgpt.internal.ChatGPTHttpResponse;
import io.github.kezhenxu94.chatgpt.internal.JsonBodyHandler;
import io.github.kezhenxu94.chatgpt.message.Message;
import io.github.kezhenxu94.chatgpt.message.SystemMessage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

final class ConversationImpl implements Conversation {
  private final ChatGPT chatGPT;
  private final String uuid;
  private final List<Message> messages;
  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  ConversationImpl(ChatGPT chatGPT, String uuid) {
    this.chatGPT = chatGPT;
    this.uuid = uuid;
    this.messages = new LinkedList<>();
    this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
    this.objectMapper = new ObjectMapper();
  }

  ConversationImpl(ChatGPT chatGPT) {
    this(chatGPT, UUID.randomUUID().toString());
  }

  ConversationImpl(ChatGPT chatGPT, SystemMessage system) {
    this(chatGPT);
    messages.add(system);
  }

  @Override
  public Message ask(String question) throws IOException, InterruptedException {
    messages.add(Message.ofUser(question));

    final var limitedMessages =
        chatGPT.conversationSize() > 0 && messages.size() > chatGPT.conversationSize()
            ? messages.subList(messages.size() - chatGPT.conversationSize(), messages.size())
            : messages;

    final var request = ChatGPTHttpRequest.builder().messages(limitedMessages).build();
    final var response =
        httpClient.send(
            HttpRequest.newBuilder()
                .uri(URI.create(ChatGPT.API_URL))
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

  @Override
  public String id() {
    return uuid;
  }

  @Override
  public List<Message> messages() {
    return messages;
  }

  @Override
  public void save() throws IOException {
    final var dataPath = chatGPT.dataPath();
    if (!dataPath.toFile().exists()) {
      //noinspection ResultOfMethodCallIgnored
      dataPath.toFile().mkdirs();
    }

    final var conversationFile = dataPath.resolve(id() + ".json").toFile();
    objectMapper.writeValue(conversationFile, messages());
  }

  @Override
  public void load() throws IOException {
    final var dataPath = chatGPT.dataPath();
    if (!dataPath.toFile().exists()) {
      return;
    }
    final var conversationFile = dataPath.resolve(id() + ".json").toFile();
    if (!conversationFile.exists()) {
      return;
    }

    final var messages =
        objectMapper.readValue(conversationFile, new TypeReference<List<Message>>() {});
    this.messages.addAll(messages);
  }

  @Override
  public void close() throws Exception {
    save();
    chatGPT.removeConversation(this);
  }
}
