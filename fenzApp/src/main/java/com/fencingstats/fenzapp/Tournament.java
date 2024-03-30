package com.fencingstats.fenzapp;

import com.fencingstats.fenzapp.Bout;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tournament name is required")
    private String name;

    @NotNull(message = "Tournament type is required")
    @Enumerated(EnumType.STRING)
    private TournamentType type;

    @NotNull(message = "Tournament date is required")
    private LocalDate date;

    private String location;

    //@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Bout> bouts = new HashSet<>();

    public Tournament(String name, TournamentType type, String location) {
        this.name = name;
        this.type = type;
        this.date = LocalDate.now();
        this.location = location;
    }

    public enum TournamentType {
        CASUAL,
        COMPETITIVE
    }

}
