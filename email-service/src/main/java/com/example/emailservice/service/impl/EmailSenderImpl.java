
package com.example.emailservice.service.impl;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.example.emailservice.model.Email;
import com.example.emailservice.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final AmazonSimpleEmailService sesClient;

    @Override
    public boolean sendEmail(Email email) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(email.getTo()))
                .withMessage(new Message()
                    .withSubject(new Content().withCharset("UTF-8").withData(email.getSubject()))
                    .withBody(new Body().withText(new Content().withCharset("UTF-8").withData(email.getBody()))))
                .withSource("your_verified_email@example.com");

            sesClient.sendEmail(request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
