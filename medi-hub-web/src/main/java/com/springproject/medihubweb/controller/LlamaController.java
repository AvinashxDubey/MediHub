package com.springproject.medihubweb.controller;

import com.springproject.medihubdata.service.LlamaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/prescription-bot")
public class LlamaController {
    private final LlamaService llamaService;

    public LlamaController(LlamaService llamaService) {
        this.llamaService = llamaService;
    }

    @PostMapping("/explain")
    public ResponseEntity<String> explainDescription(@RequestBody Map<String, String> request){
        String userInput = request.get("query");

        String prompt = "Explain this prescription in simple, educational terms. " +
                "Do not diagnose. Be informative only:\n\n" + userInput;

        String aiReply= llamaService.askLlama(prompt) +
                "\n\n⚠️ Note: This explanation is for educational purposes only. Always consult a licensed medical professional for diagnosis or treatment.";
        return ResponseEntity.ok(aiReply);
    }
}
