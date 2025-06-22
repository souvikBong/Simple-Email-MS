
package com.example.emailservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("\${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("\${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("\${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonSimpleEmailService sesClient() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
            .withRegion(region)
            .withCredentials(new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey)))
            .build();
    }

    @Bean
    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder.standard()
            .withRegion(region)
            .withCredentials(new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey)))
            .build();
    }
}
