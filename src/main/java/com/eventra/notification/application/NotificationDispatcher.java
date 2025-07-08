package com.eventra.notification.application;

import com.eventra.notification.domain.model.Notification;
import com.eventra.notification.domain.ports.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationDispatcher {

    private final List<NotificationStrategy> strategies;

    public void dispatch(Notification notification) {
        strategies.stream()
                .filter(strategy -> strategy.supports(notification))
                .findFirst()
                .ifPresentOrElse(
                        strategy -> strategy.send(notification),
                        () -> {
                            throw new IllegalStateException("❌ No strategy found for type: " + notification.getType());
                        }
                );
    }
}
