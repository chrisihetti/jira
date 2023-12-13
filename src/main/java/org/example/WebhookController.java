package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class WebhookController {

    @PostMapping("/webhook")
    public ResponseEntity<Void> handleWebhook(@RequestBody String payload) {

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert the object to JSON string
            String json = objectMapper.writeValueAsString(payload);
            JsonNode node = objectMapper.readTree(json);
            // Print the formatted JSON string
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // Process the webhook payload received from Jira
        // Implement your custom logic here

        // Optionally, you can return a response to Jira if required
        return ResponseEntity.ok().build();
    }

}