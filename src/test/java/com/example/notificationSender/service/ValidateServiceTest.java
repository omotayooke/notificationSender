package com.example.notificationSender.service;

import com.example.notificationSender.model.Notification;
import com.example.notificationSender.model.NotificationContent;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class ValidateServiceTest {

    @Autowired
    Validator validator;
    @Autowired
    ValidateService validateService;

    @Test
    void testValidate() {
       validateService.validate(createNotification());
    }

    @Test
    void testValidateIllegalArgumentException() {
        Notification notification = createNotification();
        notification.setNotificationUrl("");
        assertThrows(IllegalArgumentException.class,
                () -> validateService.validate(notification));

    }

    @Test
    void testValidateReportUIDEmpty() {
        validateService.validate(createNotification());
    }

    private Notification createNotification(){
        return Notification.builder()
                .notificationUrl("https://webhook.site/df10f1b9-b18c-430e-b91f-c8635f7d83eb")
                .notificationContent( new NotificationContent("20fb8e02-9c55-410a-93a9-489c6f1d7598","9998e02-9c55-410a-93a9-489c6f789798"))
                .build();
    }
}