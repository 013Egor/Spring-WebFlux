package com.egor.calculator.model;

public class Result {

    int iteration;
    Double answer;
    String name;
    Long time;

    public Result(int iteration, Double answer, String name, Long time) {
        this.iteration = iteration;
        this.answer = answer;
        this.name = name;
        this.time = time;
    }
    public int getIteration() {
        return iteration;
    }
    public void setIteration(int iteration) {
        this.iteration = iteration;
    }
    public Double getAnswer() {
        return answer;
    }
    public void setAnswer(Double answer) {
        this.answer = answer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getTime() {
        return time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return iteration + ",\"" + name + "\"," + answer + "," + time;
    }
     
}
