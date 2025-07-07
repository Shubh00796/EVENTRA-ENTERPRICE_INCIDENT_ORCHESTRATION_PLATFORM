package com.eventra.notification.domain.model;


import com.eventra.notification.domain.enmus.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    private NotificationType type;
    private String recipient;
    private String subject; // optional
    private String message;
    private Map<String, String> metadata;

}
