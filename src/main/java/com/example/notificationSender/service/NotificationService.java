package com.example.notificationSender.service;

import com.example.notificationSender.model.Notification;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class NotificationService {

    private final ObjectMapper objectMapper;
    private final ValidateService validateService;

    public NotificationService(ObjectMapper objectMapper, ValidateService validateService) {
        this.objectMapper = objectMapper;
        this.validateService = validateService;
    }

    public void runProcess(String filePath) throws IOException, InterruptedException {
        Notification notification = readNotificationFromFile(filePath);
        sendNotification(notification);
    }


    private Notification readNotificationFromFile(String filePath) throws IOException {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("No file path provided.");
        }

        if (!UtilService.isJsonFile(filePath)){
            throw new IllegalArgumentException(".json file expected");
        }

        String jsonContent = Files.readString(Path.of(filePath));
        Notification notification = objectMapper.readValue(jsonContent, Notification.class);
        validateService.validate(notification);
        String url = notification.getNotificationUrl();
        if(!UtilService.isValidUrl(url)){
            throw new IllegalArgumentException("Invalid URL: " + url);
        }
        return notification;
    }

    private void sendNotification(Notification notification) throws IOException, InterruptedException {
        String url = notification.getNotificationUrl();
        String requestBody = notification.getNotificationContent().toString();

        System.out.println("Notification URL: " + url);
        System.out.println("HTTP Request Body: " + requestBody);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        long startTime = System.nanoTime();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        long endTime = System.nanoTime();

        long durationMs = (endTime - startTime) / 1_000_000;

        System.out.println("HTTP Response Code: " + response.statusCode());
        System.out.println("HTTP Response Body: " + response.body());
        System.out.println("Response Time (ms): " + durationMs);
    }
}
