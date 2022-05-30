package com.example.drivingquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.drivingquiz.info.InfoActivity;
import com.google.android.material.navigation.NavigationView;

public class CategoriesActivity extends MainActivity {
    NavigationView navigationView;
    private Button btnQuestionsAlcohol;
    private Button btnInfoAlcohol;
    private Button btnQuestionsIntersections;
    private Button btnInfoIntersections;
    private Button btnQuestionsDefensiveDriving;
    private Button btnInfoDefensiveDriving;
    private Button btnQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        btnInfoAlcohol = findViewById(R.id.btnInfoAlcohol);
        btnInfoAlcohol.setOnClickListener(view -> createIntentInfo("Alcohol and Drugs"));
        btnQuestionsAlcohol = findViewById(R.id.btnQuestionsAlcohol);
        btnQuestionsAlcohol.setOnClickListener(view -> createIntentQuestionTest("Alcohol and Drugs questions"));

        btnInfoIntersections = findViewById(R.id.btnInfoIntersections);
        btnInfoIntersections.setOnClickListener(view -> createIntentInfo("Intersections"));
        btnQuestionsIntersections = findViewById(R.id.btnQuestionsIntersections);
        btnQuestionsIntersections.setOnClickListener(view -> createIntentQuestionTest("Intersections"));

        btnInfoDefensiveDriving = findViewById(R.id.btnInfoDefensiveDriving);
        btnInfoDefensiveDriving.setOnClickListener(view -> createIntentInfo("Defensive Driving"));
        btnQuestionsDefensiveDriving = findViewById(R.id.btnQuestionsDefensiveDriving);
        btnQuestionsDefensiveDriving.setOnClickListener(view -> createIntentQuestionTest("Defensive Driving"));

        btnQuiz = findViewById(R.id.btnQuiz);
        btnQuiz.setOnClickListener(view -> createIntentQuestionTest("ALL"));
    }


    private void createIntentQuestionTest(String category) {
        Intent intent = new Intent(CategoriesActivity.this, Quiz.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }

    private void createIntentInfo(String category) {
        Intent intent = new Intent(CategoriesActivity.this, InfoActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}