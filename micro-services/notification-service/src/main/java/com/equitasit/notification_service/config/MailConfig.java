package com.equitasit.notification_service.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.ses.SimpleEmailServiceMailSender;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
public class MailConfig {
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {

        return AmazonSimpleEmailServiceClientBuilder.standard()
//          .withCredentials(new ProfileCredentialsProvider("default"))
                .withCredentials(new ProfileCredentialsProvider())
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Bean
    public MailSender mailSender(
            AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}