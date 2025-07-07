package com.eventra.notification.domain;

import com.eventra.notification.domain.adapters.EmailClient;
import com.eventra.notification.domain.enmus.NotificationType;
import com.eventra.notification.domain.model.Notification;
import com.eventra.notification.domain.ports.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationStrategy {

    private final EmailClient emailClient;

    @Override
    public boolean supports(Notification notification) {
        return notification.getType() == NotificationType.EMAIL;
    }

    @Override
    public void send(Notification notification) {
        emailClient.sendEmail(
                notification.getRecipient(),
                "",
                notification.getSubject(),
                notification.getMessage()
        );

    }
}
