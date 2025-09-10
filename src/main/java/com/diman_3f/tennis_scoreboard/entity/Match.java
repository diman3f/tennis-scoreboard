package com.diman_3f.tennis_scoreboard.entity;

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
    private int ID;
    @ManyToOne
    @JoinColumn(name = "Player1")
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "Player2")
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "Winner")
    private Player winner;
}
