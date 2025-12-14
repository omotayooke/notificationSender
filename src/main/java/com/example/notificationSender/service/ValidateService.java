package com.example.notificationSender.service;

import com.example.notificationSender.model.Notification;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ValidateService {

    private final Validator validator;

    public ValidateService(Validator validator) {
        this.validator = validator;
    }

    public void validate(Notification notification) {
        Set<ConstraintViolation<Notification>> errors = validator.validate(notification);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(formatValidationErrors(errors));
        }
    }

    private String formatValidationErrors(Set<? extends ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(", "));
    }
}
