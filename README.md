# ChatGPT Java SDK

## Add the dependency

![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Fio%2Fgithub%2Fkezhenxu94%2Fchatgpt-java-sdk%2Fmaven-metadata.xml)

### Maven

```xml

<dependency>
    <groupId>io.github.kezhenxu94</groupId>
    <artifactId>chatgpt-java-sdk</artifactId>
    <version>${chatgpt-java.version}</version>
</dependency>
```

### Gradle

```groovy
implementation 'io.github.kezhenxu94:chatgpt-java-sdk:${chatgpt-java.version}'
```

## Usage

```java
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
```

A demo usage of ChatGPT Java SDK is the [ChatGPT CLI](#chatgpt-cli), built with Spring Shell and Graalvm.

# ChatGPT CLI

> Who on earth would want to use CLI to interact with ChatGPT? Developers!

## Prerequisites

An API key from OpenAI platform is required before using the CLI, head
to [OpenAI Account](https://platform.openai.com/account/api-keys) to create one.

```shell
export CHATGPT_API_KEY=<your-api-key>
```

## Download the CLI

Head to [Releases](https://github.com/kezhenxu94/chatgpt-java/releases) to download the CLI according to your os.

```shell
sudo install chatgpt-cli.macos /usr/local/bin/chatgpt
```

## Running the CLI in Docker

```shell
docker run -it --rm -e CHATGPT_API_KEY kezhenxu94/chatgpt-cli
```

## Usage

### Conversation (Interactive Mode)

`chatgpt-cli` by default runs in interactive mode and the questions in one session is a conversation, so ChatGPT
remembers
your previous questions inside a single conversation, you can use `ask` command to ask ChatGPT any question:

```shell
chatgpt
> ask --question 'What is your name?'
```

Or you can omit `--question` and simply:

```shell
chatgpt
> ask "What is your name?"
> ask "What did I ask you?"
# should reply something like "You asked for my name."
```

But don't forget to quote the question if it contains spaces.

### One-shot (Non-Interactive Mode)

If you want to ask ChatGPT a one-shot question, you can pass the `ask` command and the question directly, so ChatGPT
will
exit after it replies your questions:

```shell
chatgpt ask "What is your name?"
```
