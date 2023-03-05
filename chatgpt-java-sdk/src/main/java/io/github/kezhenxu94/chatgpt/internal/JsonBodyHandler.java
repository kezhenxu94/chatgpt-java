package io.github.kezhenxu94.chatgpt.internal;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class JsonBodyHandler<W> implements HttpResponse.BodyHandler<W> {
  private final Class<W> clazz;

  public JsonBodyHandler(Class<W> clazz) {
    this.clazz = clazz;
  }

  @Override
  public HttpResponse.BodySubscriber<W> apply(HttpResponse.ResponseInfo responseInfo) {
    return asJSON(clazz);
  }

  public static <T> HttpResponse.BodySubscriber<T> asJSON(Class<T> clazz) {
    final var upstream = HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8);

    return HttpResponse.BodySubscribers.mapping(
        upstream,
        body -> {
          try {
            final var objectMapper = new ObjectMapper();
            return objectMapper.readValue(body, clazz);
          } catch (IOException e) {
            throw new UncheckedIOException(e);
          }
        });
  }
}
