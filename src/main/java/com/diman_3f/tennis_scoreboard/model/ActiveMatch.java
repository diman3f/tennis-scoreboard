package com.diman_3f.tennis_scoreboard.model;


import com.diman_3f.tennis_scoreboard.entity.Player;
import com.diman_3f.tennis_scoreboard.service.Score;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveMatch {

    private Long playerOneID ;
    private Long playerTwoID ;
    private Score score;
}
