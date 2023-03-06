# ChatGPT Java SDK

## Add the dependency

```xml
<dependency>
    <groupId>io.github.kezhenxu94</groupId>
    <artifactId>chatgpt-java-sdk</artifactId>
    <version>${chatgpt-java.version}</version>
</dependency>
```

## Usage

```java
public class ChatGPTTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        final var chatGPT = ChatGPT.builder().build();
        final var conversation = chatGPT.newConversation();
        System.out.println(conversation.ask("What's your name?").content());
        // Output: I'm an AI language model developed by OpenAI, and I don't have a name. What can I help you with today?
        System.out.println(conversation.ask("What did I ask you?").content());
        // Output: You asked for my name.

        final var conversation2 = chatGPT.newConversation("You are a software engineer.");
        System.out.println(conversation2.ask("What's your job?").content());
        // Output: As a software engineer, my job involves designing, developing, testing, and maintaining software systems and applications. It can involve tasks such as writing code, debugging programs, troubleshooting issues, and collaborating with other team members to ensure the overall functionality and efficiency of the software being developed. I may also need to work on improving existing software, conducting research to identify new technologies or methods that could benefit my team, and keeping up with industry trends and best practices to continuously improve my skills and knowledge.
        System.out.println(conversation2.ask("What's your day to day work?").content());
        // Output: As an AI language model, I do not have a physical day-to-day work environment. However, as a software engineer, a typical day may involve various activities such as:
    }
}
```

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
chatgpt
> ask 'What is your name?'
```

## Running the CLI in Docker

```shell
docker run -it --rm -e CHATGPT_API_KEY kezhenxu94/chatgpt-cli
> ask 'What is your name?'
```
