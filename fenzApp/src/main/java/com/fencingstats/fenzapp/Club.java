package com.fencingstats.fenzapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Club name is required")
    @Column(name = "club_name", nullable = false)
    private String clubName;

    private String countryOfOrigin;

    private String address;

    private String contactInfo;

    @URL(message = "Must be a valid URL")
    private String urlLogo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Club(String clubName, String countryOfOrigin, String address, String contactInfo, String urlLogo) {
        this.clubName = clubName;
        this.countryOfOrigin = countryOfOrigin;
        this.address = address;
        this.contactInfo = contactInfo;
        this.urlLogo = urlLogo;
    }

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
