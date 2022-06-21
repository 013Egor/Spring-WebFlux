package com.egor.calculator.model;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Code {

    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine engine = manager.getEngineByName("JavaScript");
    private String code;
    private String functionName;
    private String name; 
    private int iteration;

    private Double answer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws ScriptException {
        this.code = code;
        int end = 9;
        for (int i = end; i < code.length(); i++) {
            if (code.charAt(i) == '(' || code.charAt(i) == ' '){
                end = i;
                break;
            }
        }
        functionName = code.substring(9, end);
        engine.eval(code);
    }

    public int getIteration() {
        return iteration;
    }

    public Code setIteration(int iteration) {
        this.iteration = iteration;

        return this;
    }

    public Double getAnswer() {
        return answer;
    }

    public void setAnswer(Double answer) {
        this.answer = answer;
    }

    public Result calculate(Double x) {
        Invocable inv = (Invocable) engine;

        long timeStart = System.currentTimeMillis();
        try {
            answer = (Double) inv.invokeFunction(functionName, x);
        } catch (NoSuchMethodException | ScriptException e) {
            answer = null;
        }
        long timeEnd = System.currentTimeMillis();
        
        return new Result(iteration, answer, name, timeEnd - timeStart);
    }

    public String toString() {
        return "iteration: " + iteration + "; function: " + functionName + "; answer: " + answer; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
