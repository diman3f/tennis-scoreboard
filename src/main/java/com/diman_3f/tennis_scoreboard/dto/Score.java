package com.diman_3f.tennis_scoreboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Score {
    private int set = 0;
    private int game = 0;
    private int point = 0;
}
