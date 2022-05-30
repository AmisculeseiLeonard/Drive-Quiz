package com.example.drivingquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class RecyclerviewQuizResults extends MainActivity {
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_quiz_results);

        RecyclerView rvQuizResults = findViewById(R.id.rvQuizResults);
        QuizResultsAdapter adapter = new QuizResultsAdapter();
        rvQuizResults.setAdapter(adapter);
        LinearLayoutManager quizResultsLayoutManager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rvQuizResults.setLayoutManager(quizResultsLayoutManager);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

}