package com.example.drivingquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        //FrameLayout frameLayout = findViewById(R.id.frame_layout);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigationView);
        navigationView.bringToFront();
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openNavBar, R.string.closeNavBAr);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Log.d("MENU", "item 1pressed");
                Intent intentCategories = new Intent(this, CategoriesActivity.class);
                startActivity(intentCategories);
                return true;
            case R.id.item2:
                Intent intentHistory = new Intent(this, RecyclerviewQuizResults.class);
                startActivity(intentHistory);
                return true;
            case R.id.item3:
                FirebaseAuth.getInstance().signOut();
                Intent intentLogout = new Intent(this, Auth.class);
                startActivity(intentLogout);
                return true;
            default:
                Log.d("MENU", "default");
                return false;
        }
    }
}