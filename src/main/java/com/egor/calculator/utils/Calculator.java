package com.egor.calculator.utils;

import java.time.Duration;
import java.util.LinkedList;

import javax.script.ScriptException;

import com.egor.calculator.model.Code;
import com.egor.calculator.model.Pair;
import com.egor.calculator.model.Result;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class Calculator {

    Code code1;
    Code code2;
    static int how;
    static int amount;

    static int it1 = 0, it2 = 0;
    static double step = 0;
    static LinkedList<Pair> donePairs = new LinkedList<Pair>();


    public Calculator(String code1, String code2, int amount, int how) throws ScriptException {
        
        this.code1 = new Code();
        this.code1.setName("Function #1");
        this.code1.setCode(code1);
        
        this.code2 = new Code();
        this.code2.setName("Function #2");
        this.code2.setCode(code2);

        Calculator.how = how;
        Calculator.amount = amount;
    }

    private Flux<Result> generateResultsOne() {
        it1 = 0;
        it2 = 0;
        step = 0;
        return Flux
        .generate(() -> 0, (state, sink) -> {
            if (state >= amount) {
                sink.complete();
            } else {
                code1.setIteration(it1++);
                sink.next(code1.calculate(step++));
            }

            return state + 1;
        });
    }

    private Flux<Result> generateResultsTwo() {
        return Flux
        .generate(() -> 0, (state, sink) -> {
            if (state >= amount) {
                sink.complete();
            } else {
                code2.setIteration(it2++);
                sink.next(code2.calculate(step++));
            }

            return state + 1;
        });
    }

    private Flux<Result> combine(Flux<Result> resultsCodeOne, Flux<Result> resultsCodeTwo) {
        Flux<Result> temp = Flux.create(sink -> {
                    
            resultsCodeOne.delaySubscription(Duration.ofSeconds(1))
            .subscribe(new BaseSubscriber<Result>() {
                @Override
                protected void hookOnNext(Result value) {
                    sink.next(value);
                }
            });

            resultsCodeTwo.delaySubscription(Duration.ofSeconds(1))
            .subscribe(new BaseSubscriber<Result>() {
                @Override
                protected void hookOnNext(Result value) {
                    sink.next(value);
                }
            });
        });

        return temp;
    }
    
    public Flux<String> calculate() {
        
        Flux<Result> resultsCodeOne = generateResultsOne();
        
        Flux<Result> resultsCodeTwo = generateResultsTwo();
                
        Flux<Result> allResults = combine(resultsCodeOne, resultsCodeTwo);

        Flux<String> outResult = Flux.push(sink -> {
            allResults
            .subscribe(new BaseSubscriber<Result>() {
                @Override
                protected void hookOnNext(Result value) {
                    if (how == 0) {
                        sink.next(value.toString());
                    } else {
                        boolean isSetted = false;
                        Pair item;
                        for (int i = 0; i < donePairs.size(); i++) {
                            item = donePairs.get(i);
                            if (item.getIteration() == value.getIteration()) {
                                isSetted = item.setResult(value);
                                if (item.isCompleted()) {
                                    sink.next(item.getLine(value, (donePairs.size() - 1)));
                                    donePairs.remove(i);
                                    break;
                                }
                            }
                        }
                        if (isSetted == false) {
                            Pair temp = new Pair();
                            temp.setResult(value);
                            temp.setIteration(value.getIteration());
                            donePairs.add(temp);
                        }
                    }
                    
                }
            });
        });

        return outResult;
    }
}