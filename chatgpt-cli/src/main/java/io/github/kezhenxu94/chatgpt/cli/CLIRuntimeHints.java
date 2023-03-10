package io.github.kezhenxu94.chatgpt.cli;

import io.github.kezhenxu94.chatgpt.cli.commands.ConversationCommands;
import lombok.SneakyThrows;
import org.springframework.aot.hint.ExecutableMode;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class CLIRuntimeHints implements RuntimeHintsRegistrar {
  @Override
  @SneakyThrows
  public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
    hints
        .reflection()
        .registerMethod(
            ConversationCommands.class.getDeclaredMethod("systemCommandAvailable"),
            ExecutableMode.INVOKE);
  }
}
