package com.diman_3f.tennis_scoreboard.models;

import lombok.Getter;
import lombok.Setter;

/**
 * структура нет поведения есть только переменные для хранения цифр
 * анализируя эту структуру, другой класс выполняет действия в соответствии с своей специализацией
 * либо меняет поля в это структуре, либо завершает игру и т.п.
 */
@Getter
@Setter
public class ScorePlayer {

    private int tieBreak = 0;
    private int equalsGame = 0; // TODO: 24.09.2025 переделать нейминг  
    private int equalsSetGame = 0; // TODO: 24.09.2025 переделать нейминг
    private int set = 0;
    private int game = 0;
    private int point = 0;
}
