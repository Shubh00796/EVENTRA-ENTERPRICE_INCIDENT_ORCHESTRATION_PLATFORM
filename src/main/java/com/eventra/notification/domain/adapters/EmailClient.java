package com.eventra.notification.domain.adapters;

import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.emails.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailClient {

    private final MailerSend mailerSend;

    @Value("${notification.email.from}")
    private String fromEmail;

    @Value("${notification.email.from-name}")
    private String fromName;

    public EmailClient(@Value("${notification.email.api-token}") String apiToken) {
        this.mailerSend = new MailerSend();
        this.mailerSend.setToken(apiToken);
    }

    public void sendEmail(String recipientEmail, String recipientName, String subject, String htmlBody) {
        try {
            Email email = buildEmail(recipientEmail, recipientName, subject, htmlBody);
            mailerSend.emails().send(email);
            log.info("✅ Email sent successfully to {}", recipientEmail);
        } catch (Exception ex) {
            log.error("❌ Failed to send email to {}: {}", recipientEmail, ex.getMessage(), ex);
            throw new RuntimeException("Email sending failed", ex);
        }
    }

    private Email buildEmail(String recipientEmail, String recipientName, String subject, String htmlBody) {
        Email email = new Email();
        email.setSubject(subject);
        email.setHtml(htmlBody);

        email.addRecipient(
                recipientName != null ? recipientName : "",
                recipientEmail
        );

        return email;
    }

}
