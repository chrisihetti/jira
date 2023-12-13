package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class WebhookController {

    Logger logger = LoggerFactory.getLogger(WebhookController.class);

    @PostMapping("/webhook")
    public ResponseEntity<Void> handleWebhook(@RequestBody String payload) {

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert the object to JSON string
            String json = objectMapper.writeValueAsString(payload);
            JsonNode node = objectMapper.readTree(payload);
            JsonNode issue = node.get("issue");

            // Print the formatted JSON string
            logger.info(json)
            logger.info(issue.get("key").toPrettyString());
            logger.info(node.get("changelog").toPrettyString());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // Process the webhook payload received from Jira
        // Implement your custom logic here

        // Optionally, you can return a response to Jira if required
        return ResponseEntity.ok().build();
    }

}