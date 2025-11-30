package com.example.p01.controller;

import java.util.Map;

import com.example.p01.vo.OpenAiVo.openAiGenerateReq;
import jakarta.validation.Valid;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OpenAiController {

    private final OpenAiChatModel chatModel;

    @Autowired
    public OpenAiController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/ai/generate")
    public Map<String, String> generate(@Valid @RequestBody openAiGenerateReq openAiGenerateReq) {
        try {
            String result =String.format(
                    "這是一位員工過去 30 天的紀錄：\n" +
                    "- 每日心情分數 (1~5)：%s\n" +
                    "- 請假次數：本月 %d 次\n" +
                    "- 意見文字：\n%s\n\n" +
                    "請根據這些資料：\n" +
                    "1. 推測這位員工的離職風險程度（低 / 中 / 高）。\n" +
                    "2. 給出風險分數 (0~100)。\n" +
                    "3. 提供具體建議，幫助降低他的離職風險。",
                    openAiGenerateReq.getMoodScore(),
                    openAiGenerateReq.getLeaveCount(),
                    openAiGenerateReq.getOpinion());

            return Map.of("generation", chatModel.call(result));
        } catch (org.springframework.ai.retry.NonTransientAiException e) {
            return Map.of("error", "OpenAI API Key 無效或連線失敗");
        } catch (Exception e) {
            return Map.of("error", "伺服器錯誤：" + e.getMessage());
        }
    }

}