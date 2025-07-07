package com.eventra.notification.domain.ports;


import com.eventra.notification.domain.model.Notification;

public interface NotificationStrategy {
    boolean supports(Notification notification);
    void send(Notification notification);
}

