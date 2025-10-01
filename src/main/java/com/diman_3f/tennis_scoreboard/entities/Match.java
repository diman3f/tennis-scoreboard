package com.diman_3f.tennis_scoreboard.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "Matchs")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "Player1")
    private int player1;

    @JoinColumn(name = "Player2")
    private int player2;

    @JoinColumn(name = "Winner")
    private int winner;
}
