
package com.example.emailservice.listener;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.example.emailservice.model.Email;
import com.example.emailservice.service.EmailSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SQSListenerService {

    private final EmailSender emailSender;
    private final AmazonSQS amazonSQS;
    private final ObjectMapper objectMapper;

    private final String statusQueueUrl = "https://sqs.us-east-1.amazonaws.com/YOUR_ACCOUNT_ID/email-status-queue";

    @SqsListener("\${sqs.email-input-queue}")
    public void listen(String messageJson) {
        try {
            Email email = objectMapper.readValue(messageJson, Email.class);
            boolean sent = emailSender.sendEmail(email);

            String status = sent ? "SUCCESS" : "FAILURE";
            amazonSQS.sendMessage(new SendMessageRequest()
                .withQueueUrl(statusQueueUrl)
                .withMessageBody(String.format("Email to %s: %s", email.getTo(), status)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
