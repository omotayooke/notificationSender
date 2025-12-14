package com.example.notificationSender.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationContent {
    @NotBlank
    private String reportUID;
    @NotBlank
    private String studyInstanceUID;
}
