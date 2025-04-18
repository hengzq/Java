package cn.hengzq.client.controller;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.McpToolUtils;
import org.springframework.ai.zhipuai.ZhiPuAiChatModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class ChatController {

    private final ZhiPuAiChatModel chatModel;

    private final List<McpSyncClient> clients;

    public ChatController(ZhiPuAiChatModel chatModel, List<McpSyncClient> clients) {
        this.chatModel = chatModel;
        this.clients = clients;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chat(String prompt) {

        ChatClient chatClient = ChatClient.builder(chatModel)
                .defaultTools(McpToolUtils.getToolCallbacksFromSyncClients(clients))
                .build();

        return chatClient.prompt(prompt)
                .stream()
                .chatResponse()
                .map(response -> {
                    return response.getResult().getOutput().getText();
                });
    }
}
