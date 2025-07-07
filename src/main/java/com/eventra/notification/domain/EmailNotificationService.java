package com.eventra.notification.domain;


public interface EmailNotificationService {
    void sendEmail(String to, String subject, String body);
}
