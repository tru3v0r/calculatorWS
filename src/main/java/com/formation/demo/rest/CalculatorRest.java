package com.formation.demo.rest;

import com.formation.demo.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorRest {

    @Autowired
    private Calculator calculator;

    @RequestMapping("/sum")
    public String sum(@RequestParam Integer a,@RequestParam Integer b) {
        return String.valueOf(calculator.sum(a,b));
    }
}
