package com.fencingstats.fenzapp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class ContactMessageDTO {
    @NotBlank(message = "Name is required.")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email address.")
    @Size(max = 100)
    private String email;
    @NotBlank(message = "Message is required.")
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
