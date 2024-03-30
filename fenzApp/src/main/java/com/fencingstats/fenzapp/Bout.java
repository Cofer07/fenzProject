package com.fencingstats.fenzapp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fencer_id_one")
    private Fencer fencerOne;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fencer_id_two")
    private Fencer fencerTwo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    private Fencer winner;

    @Enumerated(EnumType.STRING)
    private BoutType boutType;

    private LocalDate date;

    private String location;

    private String score;

    public Bout(Long id, Fencer fencerOne, Fencer fencerTwo, Fencer winner, BoutType boutType, LocalDate date, String location, String score) {
        this.id = id;
        this.fencerOne = fencerOne;
        this.fencerTwo = fencerTwo;
        this.winner = winner;
        this.boutType = boutType;
        this.date = LocalDate.now();
        this.location = location;
        this.score = score;
    }

    public enum BoutType {
        CASUAL,
        COMPETITIVE
    }
}
