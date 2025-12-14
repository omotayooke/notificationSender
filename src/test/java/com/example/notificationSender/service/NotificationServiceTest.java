package com.example.notificationSender.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class NotificationServiceTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    ValidateService validateService;

    @Autowired
    NotificationService notificationService;

    @Test
    void testRunProcess() throws IOException, InterruptedException {
        String testJson = """
                {
                  "notificationUrl": "https://webhook.site/test-url",
                  "notificationContent": {
                    "reportUID": "1234",
                    "studyInstanceUID": "5678"
                  }
                }
                """;
        String tempFile = createJsonFile(testJson, ".json");

        notificationService.runProcess(tempFile);
        Files.deleteIfExists(Path.of(tempFile));
    }

    @Test
    void testRunProcessInvalidFileType() throws IOException, InterruptedException {
        String tempFile = createJsonFile("testString", ".txt");
        assertThrows(IllegalArgumentException.class,
                () -> notificationService.runProcess(tempFile));
        Files.deleteIfExists(Path.of(tempFile));
    }

    @Test
    void testRunProcessWithEmptyFile() throws IOException, InterruptedException {
        String tempFile = "";
        assertThrows(IllegalArgumentException.class,
                () -> notificationService.runProcess(tempFile));
    }

    @Test
    void testExceptionForInvalidUrl() throws IOException, InterruptedException {
        String testJson = """
                {
                  "notificationUrl": "https:/webhook.site/test-url",
                  "notificationContent": {
                    "reportUID": "1234",
                    "studyInstanceUID": "5678"
                  }
                }
                """;
        String tempFile = createJsonFile(testJson, ".json");
        assertThrows(IllegalArgumentException.class,
                () -> notificationService.runProcess(tempFile));
        java.nio.file.Files.deleteIfExists(Path.of(tempFile));
    }

    private String createJsonFile(String jsonString, String fileExtension) throws IOException {
        Path tempFile = Files.createTempFile("notification", fileExtension);
        Files.writeString(tempFile, jsonString);
        return tempFile.toString();
    }
}