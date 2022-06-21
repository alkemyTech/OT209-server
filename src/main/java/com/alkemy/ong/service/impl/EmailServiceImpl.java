package com.alkemy.ong.service.impl;

import java.io.IOException;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alkemy.ong.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String SUBJECT = "Gracias por registrarte";
    private static final String TYPE_CONTENT = "text/html";
    private static final String BODY_CONTENT = "bodyyy";
    private static final String REQUEST_ENDPOINT = "mail/send";

    @Value("${ong.sendgrid.frommail}")
    private String fromEmail;
    @Value("${ong.sendgrid.idtemplate}")
    private String templateId;
    @Value("${ong.sendgrid.apikey}")
    private String sendgridApiKey;

    public void sendTemplateSolosMas(String toMail) throws IOException {
        Email from = new Email(fromEmail);
        String subject = SUBJECT;
        Email to = new Email(toMail);
        Content content = new Content(TYPE_CONTENT, BODY_CONTENT);
        Mail mail = new Mail(from, subject, to, content);

        mail.setTemplateId(templateId);

        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint(REQUEST_ENDPOINT);
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("-------------------------------------------");
            System.out.println("EMAIL DATA:");
            System.out.println(response.getStatusCode() + "\n");
            System.out.println(response.getBody() + "\n");
            System.out.println(response.getHeaders() + "\n");
            System.out.println("-------------------------------------------");
        } catch (IOException ex) {
            throw ex;
        }
    }
}
