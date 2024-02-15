package com.littlesekii.emailservice.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.littlesekii.emailservice.adapters.EmailSenderGateway;
import com.littlesekii.emailservice.core.exceptions.EmailServiceException;
import org.springframework.stereotype.Service;
@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }


    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest();

        for (int i = 1; i <= 50; i++) {
            request = request
                    .withSource("littlesekii@gmail.com")
                    .withDestination(new Destination()
                            .withToAddresses(to)
                    )
                    .withMessage(new Message()
                            .withSubject(new Content(subject))
                            .withBody(new Body()
                                    .withText(new Content(body + i))
                            )
                    );

            try {

                this.amazonSimpleEmailService.sendEmail(request);
            } catch (AmazonServiceException exception) {
                throw new EmailServiceException("Failure while sending email.", exception);
            }
        }
    }
}
