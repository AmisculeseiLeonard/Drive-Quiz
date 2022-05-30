package com.example.drivingquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drivingquiz.model.Question;
import com.example.drivingquiz.utils.FirebaseUtils;
import com.example.drivingquiz.utils.QuestionRandomizer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Quiz extends MainActivity {
    private Button btnNextQuestion, btnSubmitAnswer;
    private EditText txtQuestion;
    private TextView txtTimer, txtQCount;
    private ImageView image;
    private RadioGroup radioGroup;
    private RadioButton option1, option2, option3;
    private int count, incorrectAnswersCount, questionsCount;
    private Query query;
    private boolean answerSubmitted;
    private QuestionRandomizer questionRandomizer;
    private String category;
    ArrayList<Question> questionsTimedQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_test);

        Intent intent = getIntent();
        category = intent.getStringExtra("CATEGORY");

        switch (category){
            case "Alcohol and Drugs questions":
                query = FirebaseUtils.getQuestionsCategoryBased("Alcohol and Drugs questions");
                break;
            case "Intersections":
                query = FirebaseUtils.getQuestionsCategoryBased("Intersections");
                break;
            case "Defensive Driving":
                query = FirebaseUtils.getQuestionsCategoryBased("Defensive Driving");
                break;
            default:
                query = FirebaseUtils.getQuestions();
                break;
        }

        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        btnSubmitAnswer = findViewById(R.id.btnSubmit);
        txtQuestion = findViewById(R.id.txtQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        image = findViewById(R.id.image);
        txtQCount = findViewById(R.id.txtCount);
        txtTimer = findViewById(R.id.txtTimer);

        questionsTimedQuiz = new ArrayList<>();
        count = 0;
        incorrectAnswersCount = 0;
        questionsCount = 0;
        answerSubmitted = false;


        readData(q -> {
            if(category.equals("ALL")){
                questionsCount = 10;
            }else{
                questionsCount = q.size();
            }
        });

        getNextQuestion();

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNextQuestion();
            }
        });

        btnSubmitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswer();
            }
        });
        btnSubmitAnswer.setClickable(false);

        if(category.equals("ALL")){
            CountDownTimer countDown;
            countDown= new CountDownTimer(600000, 1000) {

                @SuppressLint("DefaultLocale")
                public void onTick(long millisUntilFinished) {

                    txtTimer.setText("Timer: " +  String.format("%02d : %02d",
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {

                }
            };
            countDown.start();
        }else{
            txtTimer.setText("Timer: " + "No time limit");
        }

        option1.setOnClickListener(view -> btnSubmitAnswer.setClickable(true));
        option2.setOnClickListener(view -> btnSubmitAnswer.setClickable(true));
        option3.setOnClickListener(view -> btnSubmitAnswer.setClickable(true));
    }

    public void getNextQuestion() {

        if(!answerSubmitted && count != 0){
            Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        if(category.equals("ALL")) {
            readData(q -> {
                if(count < questionsCount){
                    Question question = questionsTimedQuiz.get(count);
                    itemsSetup(question);
                }else{
                    getResults();
                }
            });
        }else{
            readData(q -> {
                if(count < q.size() || category.equals("ALL")){
                    Question question = q.get(count);
                    itemsSetup(question);
                }else{
                    getResults();
                }
            });
        }


        txtQCount.setText(count+1 + "/" + questionsCount);
        count++;
        resetItems();
    }

    public void submitAnswer() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);

        if(category.equals("ALL")) {
            Question question = questionsTimedQuiz.get(count);
            Log.d("MAP", question.getQuestion());
            boolean answer = question.getOptions().get(radioButton.getText());
            if(answer){
                radioButton.setBackgroundColor(Color.GREEN);
            }else {
                radioButton.setBackgroundColor(Color.RED);
                incorrectAnswersCount++;
            }
        }else{
            readData(q -> {
                Question question = q.get(count);

                Log.d("MAP", question.getQuestion());
                boolean answer = question.getOptions().get(radioButton.getText());
                if(answer){
                    radioButton.setBackgroundColor(Color.GREEN);
                }else {
                    radioButton.setBackgroundColor(Color.RED);
                    incorrectAnswersCount++;
                }
            });
        }

        answerSubmitted = true;
        blockItems();
        btnSubmitAnswer.setClickable(false);
    }

    public void resetItems() {
        image.setImageDrawable(null);
        option1.setBackgroundColor(android.R.drawable.btn_radio);
        option2.setBackgroundColor(android.R.drawable.btn_radio);
        option3.setBackgroundColor(android.R.drawable.btn_radio);
        radioGroup.clearCheck();
        answerSubmitted = false;
        option1.setClickable(true);
        option2.setClickable(true);
        option3.setClickable(true);
        //btnSubmitAnswer.setClickable(true);

    }

    public void blockItems() {
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);
        btnSubmitAnswer.setClickable(false);
    }

    public void itemsSetup(Question question) {
        txtQuestion.setText(question.getQuestion());
        String[] options = question.getOptions().keySet().toArray(new String[0]);
        option1.setText(options[0]);
        option2.setText(options[1]);
        option3.setText(options[2]);


        if(question.getImage() != null && !question.getImage().equals("")){
            Picasso.get().load(question.getImage()).resize(1000,500).into(image);
        }else{
            Picasso.get().load(R.drawable.quiz).resize(0,1).into(image);
        }

    }

    public void getResults() {
        Intent intent = new Intent(Quiz.this, QuizResult.class);
        intent.putExtra("questionsCount", questionsCount);
        intent.putExtra("incorrectAnswersCount", incorrectAnswersCount);
        intent.putExtra("correctAnswersCount", count - incorrectAnswersCount);
        intent.putExtra("questionsAnswered", count);
        intent.putExtra("category", category);
        startActivity(intent);
    }


    public void readData(DataInitializer dataInitializer) {
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Question> questions = new ArrayList<>();

                for(DataSnapshot r: snapshot.getChildren()){
                    Question question = r.getValue(Question.class);
                    question.setId(r.getKey());

                    Map<String,Boolean> optionsMap = new HashMap<>();
                    optionsMap.put((String)r.child("option1").child("answer").getValue(),
                            (Boolean) r.child("option1").child("val").getValue());
                    optionsMap.put((String)r.child("option2").child("answer").getValue(),
                            (Boolean) r.child("option2").child("val").getValue());
                    optionsMap.put((String)r.child("option3").child("answer").getValue(),
                            (Boolean) r.child("option3").child("val").getValue());

                    question.setOptions(optionsMap);
                    questions.add(question);
                }

                if(category.equals("ALL")){
                    questionRandomizer = new QuestionRandomizer(questions.size());
                    questionsTimedQuiz.add(questions.get(questionRandomizer.getIndex()));
                    dataInitializer.onCallback(questionsTimedQuiz);
                }else{
                    dataInitializer.onCallback(questions);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "readData:onCancelled", error.toException());
            }
        });
    }


}