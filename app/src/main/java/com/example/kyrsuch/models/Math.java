package com.example.kyrsuch.models;

public class Math {

    private String text, correct, incor1, incor2, incor3;

    public Math() {}

    public Math(String text, String correct, String incor1, String incor2, String incor3) {
        this.text = text;
        this.correct = correct;
        this.incor1 = incor1;
        this.incor2 = incor2;
        this.incor3 = incor3;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getIncor1() {
        return incor1;
    }

    public void setIncor1(String incor1) {
        this.incor1 = incor1;
    }

    public String getIncor2() {
        return incor2;
    }

    public void setIncor2(String incor2) {
        this.incor2 = incor2;
    }

    public String getIncor3() {
        return incor3;
    }

    public void setIncor3(String incor3) {
        this.incor3 = incor3;
    }
}
