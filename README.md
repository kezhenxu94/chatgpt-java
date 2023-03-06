# ChatGPT Java SDK and CLI

> Who on earth would want to use CLI to interact with ChatGPT? Developers!

## Prerequisites

An API key from OpenAI platform is required before using the CLI, head
to [OpenAI Account](https://platform.openai.com/account/api-keys) to create one.

```shell
export CHATGPT_API_KEY=<your-api-key>
```

## Running the CLI in Docker

```shell
docker run -it --rm -e CHATGPT_API_KEY kezhenxu94/chatgpt-cli
```
