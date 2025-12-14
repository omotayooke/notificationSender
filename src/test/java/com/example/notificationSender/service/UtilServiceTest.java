package com.example.notificationSender.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UtilServiceTest {

    @Test
    void testIsValidUrl() {
        boolean result = UtilService.isValidUrl("https://webhook.site/df10f1b9-b18c-430e-b91f-c8635f7d83eb");
        Assertions.assertTrue(result);
    }

    @Test
    void testUrlIsNotValid() {
        boolean result = UtilService.isValidUrl("url");
        Assertions.assertFalse(result);
    }

    @Test
    void testIsJsonFile() {
        boolean result = UtilService.isJsonFile("fileName.json");
        Assertions.assertTrue(result);
    }

    @Test
    void testIsNotJsonFile() {
        boolean result = UtilService.isJsonFile("fileName.txt");
        Assertions.assertFalse(result);
    }
}
