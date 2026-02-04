package com.diman_3f.tennis_scoreboard.models;

import com.diman_3f.tennis_scoreboard.dto.Score;
import lombok.Getter;
import lombok.Setter;

/**
 * структура нет поведения есть только переменные для хранения цифр
 * анализируя эту структуру, другой класс выполняет действия в соответствии с своей специализацией
 * либо меняет поля в это структуре, либо завершает игру и т.п.
 */
@Getter
@Setter
public class ScoreModel {
    private int setOne;
    private int setTwo;
    private int gameOne;
    private int gameTwo;
    private int pointOne;
    private int pointTwo;

}
