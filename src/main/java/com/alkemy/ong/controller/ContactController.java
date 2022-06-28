package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.ContactRequest;
import com.alkemy.ong.models.response.ContactResponse;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contacts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping
    public ResponseEntity<String> messageFromContact(@Valid @RequestBody ContactRequest request) throws IOException {

        contactService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Your message has been successfully received!");
    }

    @GetMapping
    public List<ContactResponse> getAllContacts() {

        return contactService.getAll();
    }

}
