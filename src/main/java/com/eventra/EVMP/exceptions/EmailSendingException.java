package com.eventra.EVMP.exceptions;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String message, Exception ex) {
        super(message);
    }
}
