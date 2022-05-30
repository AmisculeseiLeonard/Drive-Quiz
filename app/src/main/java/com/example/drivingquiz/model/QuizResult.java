package com.example.drivingquiz.model;

public class QuizResult {
    private int totalQuestions;
    private int incorrectAnswersCount;
    private int correctAnswersCount;
    private int questionsAnswered;
    private String result;
    private String date;

    public QuizResult() {}

    public QuizResult(int questionsCount, int incorrectAnswersCount, int correctAnswersCount,
                      int questionsAnswered, String result, String date) {
        this.totalQuestions = questionsCount;
        this.incorrectAnswersCount = incorrectAnswersCount;
        this.correctAnswersCount = correctAnswersCount;
        this.questionsAnswered = questionsAnswered;
        this.result = result;
        this.date = date;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int questionsCount) {
        this.totalQuestions = questionsCount;
    }

    public int getIncorrectAnswersCount() {
        return incorrectAnswersCount;
    }

    public void setIncorrectAnswersCount(int incorrectAnswersCount) {
        this.incorrectAnswersCount = incorrectAnswersCount;
    }

    public int getCorrectAnswersCount() {
        return correctAnswersCount;
    }

    public void setCorrectAnswersCount(int correctAnswersCount) {
        this.correctAnswersCount = correctAnswersCount;
    }

    public int getQuestionsAnswered() {
        return questionsAnswered;
    }

    public void setQuestionsAnswered(int questionsAnswered) {
        this.questionsAnswered = questionsAnswered;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "totalQuestions=" + totalQuestions +
                ", incorrectAnswersCount=" + incorrectAnswersCount +
                ", correctAnswersCount=" + correctAnswersCount +
                ", questionsAnswered=" + questionsAnswered +
                ", result='" + result + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
