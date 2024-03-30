package com.fencingstats.fenzapp;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Fencer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String firstName;

    @Column(nullable=false)
    private String lastName;

    private String countryOfOrigin;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "id", nullable = true)
    private Club club;

    private String bio;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Fencer(Long id, String firstName, String lastName, String countryOfOrigin, User user, Club club, String bio, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.countryOfOrigin = countryOfOrigin;
        this.user = user;
        this.club = club;
        this.bio = bio;
        this.gender = gender;
    }

    public enum Gender {
        MALE, FEMALE, OTHER
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
