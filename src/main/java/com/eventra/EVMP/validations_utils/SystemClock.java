package com.eventra.EVMP.validations_utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SystemClock {
    public LocalDateTime now() {
        return LocalDateTime.now(); // You could inject a java.time.Clock here if needed
    }
}
