package com.egor.calculator.model;

public class Pair {
    Result r1 = null;
    Result r2 = null;
    int iteration;

    public Pair(){}
    @Override
    public String toString() {
        return iteration + "," + r1.getName() + "," + r1.getTime() + "," + r2.getName() + "," + r2.getTime();
    }
    public String getResult() {
        return r1 + " -- " + r2;
    }
    public boolean setResult(Result r) {
        if (r1 == null) {
            r1 = r;
            return true;
        } else if (r2 == null) {
            r2 = r;
            return true;
        } else {
            return false;
        }
    }

    public boolean isCompleted() {
        return r1 != null & r2 != null;
    }
    public int getIteration() {
        return iteration;
    }
    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public String getLine (Result result, int amount) {
        String resultAmount1 = "";
        String resultAmount2 = "";
        if (result.getName().equals(r1.getName())) {
            resultAmount1 = "0";
            resultAmount2 += amount;
        } else {
            resultAmount1 += amount;
            resultAmount2 = "0";
        }

        return iteration + ",\"" + r1.getName() + "\"," + r1.getTime() + "," +resultAmount1 + ",\"" + r2.getName() + "\"," + r2.getTime() + "," + resultAmount2;
    }
}
