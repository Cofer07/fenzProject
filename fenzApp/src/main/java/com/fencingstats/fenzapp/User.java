package com.fencingstats.fenzapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 6, max = 20, message = "Username must be between 6 and 20 characters long")
    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "Username must contain only letters, numbers, periods, underscores, and hyphens")
    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min=8, max = 20, message = "Password must be between 8 and 20 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "Password must contain one digit, one lowercase letter, one uppercase letter, and one special character")
    @Column(nullable = false, length = 20)
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Fencer fencerProfile;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public User(Long id, String username, String password, String email, String role, Fencer fencerProfile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.fencerProfile = fencerProfile;
    }

    public User() {
    }

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Fencer getFencerProfile() {
        return fencerProfile;
    }

    public void setFencerProfile(Fencer fencerProfile) {
        this.fencerProfile = fencerProfile;
    }

    public enum Role {
        USER, ADMIN
    }

}
