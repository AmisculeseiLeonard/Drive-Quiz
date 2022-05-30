package com.example.drivingquiz.info;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.drivingquiz.MainActivity;
import com.example.drivingquiz.R;
import com.google.android.material.navigation.NavigationView;

public class InfoActivity extends MainActivity {
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String category = intent.getStringExtra("CATEGORY");


        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        TextView txtDetails = (TextView) findViewById(R.id.txtInfo);
        TextView txtTitle = findViewById(R.id.txtTitle);

        switch (category) {
            case "Alcohol and Drugs":
                txtTitle.setText(category);
                txtDetails.setText(R.string.alcoholAndDrugsInfo);
                break;
            case "Intersections":
                txtTitle.setText(category);
                txtDetails.setText(R.string.Intersections);
                break;
            case "Defensive Driving":
                txtTitle.setText(category);
                txtDetails.setText(R.string.DefensiveDriving);
                break;
            default:
                break;
        }
        txtDetails.setMovementMethod(new ScrollingMovementMethod());




    }
}