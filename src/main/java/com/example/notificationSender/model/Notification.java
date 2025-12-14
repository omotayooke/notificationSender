package com.example.notificationSender.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Notification {
    @NotBlank
    private String notificationUrl;
    @Valid
    private NotificationContent notificationContent;
}
