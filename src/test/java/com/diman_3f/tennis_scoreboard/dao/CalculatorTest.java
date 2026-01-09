package com.diman_3f.tennis_scoreboard.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
     void sum() {
        Calculator calculator = new Calculator();
        int sum = calculator.sum(10,10);
        Assertions.assertEquals(20, sum);
    }
}
