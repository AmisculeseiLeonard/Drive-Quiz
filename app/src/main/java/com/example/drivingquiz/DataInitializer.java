package com.example.drivingquiz;

import com.example.drivingquiz.model.Question;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public interface DataInitializer {
    void onCallback(ArrayList<Question> questions);
}
