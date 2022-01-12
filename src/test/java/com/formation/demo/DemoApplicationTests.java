package com.formation.demo;

import com.formation.demo.service.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

    private Calculator calcul = new Calculator();

    @Test
    void testSum() {
        assertEquals(5, calcul.sum(2,3));
    }

}
