package com.fencingstats.fenzapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

@Service
public class ContactService {

    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public ContactService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public void saveMessage(ContactMessageDTO contactMessageDto) {
        PolicyFactory policy = new HtmlPolicyBuilder().allowCommonBlockElements().allowCommonInlineFormattingElements().toFactory();

        String sanitizedMessage = policy.sanitize(contactMessageDto.getMessage());

        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setName(contactMessageDto.getName());
        contactMessage.setEmail(contactMessageDto.getEmail());
        contactMessage.setMessage(sanitizedMessage);

        contactMessageRepository.save(contactMessage);
    }
}
