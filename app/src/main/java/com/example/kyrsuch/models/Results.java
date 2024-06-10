package com.example.kyrsuch.models;

public class Results {

    private String email, lesson, result;
    public Results(){}

    public Results(String email, String lesson, String result) {
        this.email = email;
        this.lesson = lesson;
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
