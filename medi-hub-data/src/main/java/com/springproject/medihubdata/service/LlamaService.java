package com.springproject.medihubdata.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LlamaService {
    private final RestTemplate restTemplate;

    public LlamaService(RestTemplateBuilder restTemplateBuild) {
        this.restTemplate = restTemplateBuild.build();
    }

    public String askLlama(String prompt){
        String url = "http://localhost:11434/api/generate";
        Map<String, Object> body = new HashMap<>();
        body.put("model", "mistral");
        body.put("prompt", prompt);
        body.put("stream", false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        try{
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            return response.getBody().get("response").toString();
        }
        catch (Exception e){
            return "Failed to connect to local AI: " + e.getMessage();
        }

    }
}
