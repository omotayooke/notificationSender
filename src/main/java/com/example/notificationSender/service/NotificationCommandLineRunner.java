package com.example.notificationSender.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class NotificationCommandLineRunner implements CommandLineRunner {
    private final NotificationService notificationService;

    public NotificationCommandLineRunner(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java -jar target/notificationSender-1.0.jar <path-to-json-file>");
            System.exit(1);
        }

        String filePath = args[0];
        try {
            notificationService.runProcess(filePath);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
