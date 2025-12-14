package com.example.notificationSender.service;

import org.apache.commons.validator.routines.UrlValidator;

public class UtilService {

    private static final UrlValidator urlValidator =
            new UrlValidator(new String[]{"http", "https"});

    public static boolean isValidUrl(String url) {
        return url != null && urlValidator.isValid(url);
    }

    public static boolean isJsonFile(String fileName) {
        return fileName != null && fileName.toLowerCase().endsWith(".json");
    }
}
