package com.fencingstats.fenzapp;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contact")
    public ResponseEntity<?> handleContactSubmission(@Valid @RequestBody ContactMessageDTO contactMessageDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        contactService.saveMessage(contactMessageDto);

        return ResponseEntity.ok("Message sent successfully");
    }


}

