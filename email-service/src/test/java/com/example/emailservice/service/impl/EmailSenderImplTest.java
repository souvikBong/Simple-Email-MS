
package com.example.emailservice.service.impl;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.example.emailservice.model.Email;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class EmailSenderImplTest {

    @Test
    public void testSendEmail() {
        AmazonSimpleEmailService ses = mock(AmazonSimpleEmailService.class);
        EmailSenderImpl sender = new EmailSenderImpl(ses);
        Email email = new Email("test@example.com", "Subject", "Body");

        sender.sendEmail(email);

        verify(ses, times(1)).sendEmail(any());
    }
}
