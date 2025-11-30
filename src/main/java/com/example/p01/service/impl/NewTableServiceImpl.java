package com.example.p01.service.impl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.p01.dao.NewTableRepository;
import com.example.p01.entity.NewTable;
import com.example.p01.service.ifs.NewTableService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;




@Service
public class NewTableServiceImpl implements NewTableService {

    private static final Logger log = LoggerFactory.getLogger(NewTableServiceImpl.class);

    private final NewTableRepository repository;

    // 提供預設空字串，避免因為未設定而出現 null 例外
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    // 正確的 endpoint path（注意這裡包含 /v1/chat/completions）
    private static final String AI_ENDPOINT = "https://api.chatanywhere.org/v1/chat/completions";

    public NewTableServiceImpl(NewTableRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void checkApiKey() {
        if (apiKey == null || apiKey.isBlank()) {
            log.warn("AI API key is EMPTY - 請確認 application.properties 或環境變數是否有設定 free.api.key");
        } else {
            apiKey = apiKey.trim();
            log.info("AI API key loaded, length={}", apiKey.length());
        }
    }

    @Override
    public List<NewTable> getAll() {
        return repository.findAll();
    }

    @Override
    public NewTable create(NewTable newRecord) {
        newRecord.setCreatedAt(LocalDateTime.now());
        return repository.save(newRecord);
    }

    @Override
    public NewTable askAI(NewTable newRecord) {
        // 先檢查必要欄位
        if (newRecord.getUserMessage() == null || newRecord.getUserMessage().isBlank()) {
            newRecord.setAssistantReply("AI 呼叫失敗: userMessage 為空");
            newRecord.setCreatedAt(LocalDateTime.now());
            return repository.save(newRecord);
        }

        // 若沒有 API key，預先處理並儲存一筆失敗紀錄（避免拋例外）
        if (apiKey == null || apiKey.isBlank()) {
            newRecord.setAssistantReply("AI 呼叫失敗: API key 未設定");
            newRecord.setCreatedAt(LocalDateTime.now());
            return repository.save(newRecord);
        }

        try {
            HttpClient client = HttpClient.newHttpClient();

            String safeUserMessage = newRecord.getUserMessage().replace("\"", "\\\"").replace("\r", " ").replace("\n", " ");
            String body = """
            		{
            		  "model":"gpt-4o-mini",
            		  "messages":[{"role":"user","content":"%s,請用繁體中文回答"}]
            		}
            		""".formatted(safeUserMessage);

            		HttpRequest request = HttpRequest.newBuilder()
            		    .uri(URI.create(AI_ENDPOINT))
            		    .header("Content-Type", "application/json")
            		    .header("Accept", "application/json")
            		    .header("Authorization", "Bearer " + apiKey)
            		    .POST(HttpRequest.BodyPublishers.ofString(body))
            		    .build();
            		
            		
            		
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int status = response.statusCode();
            String bodyStr = response.body();
            String contentType = response.headers().firstValue("Content-Type").orElse("");

            log.info("AI HTTP status: {}, Content-Type: {}", status, contentType);
            log.debug("AI raw body: {}", bodyStr == null ? "" : (bodyStr.length() > 2000 ? bodyStr.substring(0,2000) + "...(truncated)" : bodyStr));

            boolean isJson = contentType.toLowerCase().contains("application/json") ||
                             contentType.toLowerCase().contains("application/problem+json") ||
                             (bodyStr != null && bodyStr.trim().startsWith("{"));

            if (status == 401) {
                // 明確處理 401
                String errDetail = bodyStr == null ? "" : bodyStr;
                newRecord.setAssistantReply("AI 呼叫失敗（401 Unauthorized）: " + errDetail);
                newRecord.setCreatedAt(LocalDateTime.now());
                return repository.save(newRecord);
            }

            if (status < 200 || status >= 300 || !isJson) {
                String shortBody = bodyStr == null ? "" : (bodyStr.length() > 2000 ? bodyStr.substring(0,2000) + "...(truncated)" : bodyStr);
                String errDetail = "AI 回傳 HTTP " + status + ", Content-Type: " + contentType + ", bodyStartsWith'<': " + (bodyStr != null && bodyStr.startsWith("<"));
                newRecord.setAssistantReply("AI 呼叫失敗: " + errDetail + " 回應內容(前2k): " + shortBody);
                newRecord.setCreatedAt(LocalDateTime.now());
                return repository.save(newRecord);
            }

            // 安全解析 JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(bodyStr);

            String reply = null;
            if (root.has("choices") && root.get("choices").isArray() && root.get("choices").size() > 0) {
                JsonNode first = root.get("choices").get(0);
                if (first.has("message") && first.get("message").has("content")) {
                    reply = first.get("message").get("content").asText();
                } else if (first.has("text")) {
                    reply = first.get("text").asText();
                }
            }

            if (reply == null || reply.isBlank()) {
                if (root.has("error")) {
                    reply = "AI 回傳錯誤: " + root.get("error").toString();
                } else {
                    reply = "AI 回傳格式異常: " + bodyStr;
                }
            }

            newRecord.setAssistantReply(reply);
            newRecord.setCreatedAt(LocalDateTime.now());
            log.info("AI 回覆文字長度: {}", reply == null ? 0 : reply.length());
            return repository.save(newRecord);

        } catch (Exception e) {
            log.error("askAI exception", e);
            String err = "AI 呼叫失敗: " + e.getClass().getSimpleName() + " - " + e.getMessage();
            newRecord.setAssistantReply(err);
            newRecord.setCreatedAt(LocalDateTime.now());
            return repository.save(newRecord);
        }
    }
    
    
    
    
    
    
    
    
}
