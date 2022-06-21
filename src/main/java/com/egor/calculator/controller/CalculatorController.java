package com.egor.calculator.controller;

import javax.script.ScriptException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egor.calculator.utils.Calculator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CalculatorController {

    String code1;
    String code2;
    int amount;
    String how;

    int cur1;
    int cur2;


    @GetMapping(path = "/calculate", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getAnswers(@RequestParam String code1, @RequestParam String code2,@RequestParam String amount, @RequestParam String how) {
        
        Calculator calculator;
        try {
            calculator = new Calculator(code1, code2, Integer.parseInt(amount), Integer.parseInt(how));
            return calculator.calculate();
        } catch (NumberFormatException | ScriptException e) {
            return Mono.just("error").flux();
        }
        
        
    }
}