package com.example.drivingquiz.model;

import java.io.Serializable;
import java.util.Map;

public class Question implements Serializable {
    private String id;
    private String question;
    private Map<String, Boolean> options;
    private String category;
    private String image;

    public Question() {
    }

    public Question(String question, String category, String image) {
        this.question = question;
        this.options = options;
        this.category = category;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, Boolean> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Boolean> options) {
        this.options = options;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "DriveQuestion{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", options=" + options +
                ", category='" + category + '\'' +
                '}';
    }
}
