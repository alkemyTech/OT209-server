package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.mapper.ContactMapper;
import com.alkemy.ong.models.request.ContactRequest;
import com.alkemy.ong.models.response.ContactResponse;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    Environment env;

    @Autowired
    ContactRepository repository;
    @Autowired
    ContactMapper mapper;

    @Override
    public void add(ContactRequest request) throws IOException {

        repository.save(mapper.request2Entity(request));
        sendContactRecievedMail(request);
    }

    @Override
    public List<ContactResponse> getAll() {
        return mapper.contactEntityList2ResponseList(repository.findAll());
    }

    public void sendContactRecievedMail(ContactRequest contactRequest) throws IOException {

        Email from = new Email("productos2222@gmail.com");
        String subject = "Somos Más ONG - Contact Registered";
        Email to = new Email(contactRequest.getEmail());
        Content content = new Content("text/plain",
                "Thank you, " + contactRequest.getName() + ", for registering at Somos Más! We'll be getting in touch soon!");
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(env.getProperty("API_KEY"));
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build()); 
        System.out.println(sg.api(request).getBody());

    }
}
