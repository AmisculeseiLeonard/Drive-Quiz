package com.example.drivingquiz.model;

import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private ArrayList<QuizResult> quizResults;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<QuizResult> getQuizResults() {
        return quizResults;
    }

    public void setQuizResults(ArrayList<QuizResult> quizResults) {
        this.quizResults = quizResults;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", quizResults=" + quizResults +
                '}';
    }
}
