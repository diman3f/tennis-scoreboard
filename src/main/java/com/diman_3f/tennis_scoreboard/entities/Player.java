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
@Table(name = "Players")

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "ID")
    private Integer id;
    @Column(name="Name", unique = true)
    private String name;
    public Player(String name) {
        this.name = name;
    }
}
