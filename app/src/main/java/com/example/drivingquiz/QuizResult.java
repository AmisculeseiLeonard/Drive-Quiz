package com.example.drivingquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drivingquiz.utils.FirebaseUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class QuizResult extends MainActivity {
    NavigationView navigationView;
    TextView txtInit, score;
    ImageView resultImage;
    int questionsCount, incorrectAnswersCount,correctAnswersCount, questionsAnswered;
    String category;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        questionsCount = intent.getIntExtra("questionsCount", 25);
        incorrectAnswersCount = intent.getIntExtra("incorrectAnswersCount", 25);
        correctAnswersCount = intent.getIntExtra("correctAnswersCount", 25);
        questionsAnswered = intent.getIntExtra("questionsCount", 25);
        category = intent.getStringExtra("category");

        txtInit = findViewById(R.id.txtInit);
        score = findViewById(R.id.score);
        resultImage = findViewById(R.id.resultImage);

        score.setText(correctAnswersCount + " / " + questionsCount);
        double percent = ((double) incorrectAnswersCount/(double)questionsCount)*100;
        if(percent >= 20.0){
            resultImage.setImageResource(R.drawable.fail_trophy);
            txtInit.setText("Not ready yet!");
            result = "Failed";
        }else{
            resultImage.setImageResource(R.drawable.trophy);
            txtInit.setText("Congratulations!");
            result = "Passed";
        }

        if(category.equals("ALL")) {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String currentDate = date.format(new Date());

            FirebaseUtils.insertQuizResult(FirebaseAuth.getInstance().getCurrentUser(),
                    questionsCount, incorrectAnswersCount, correctAnswersCount, questionsAnswered, result,currentDate );
        }



    }
}