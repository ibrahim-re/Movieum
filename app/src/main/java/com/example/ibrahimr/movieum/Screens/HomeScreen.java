package com.example.ibrahimr.movieum.Screens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ibrahimr.movieum.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeScreen extends AppCompatActivity {

    Button goToCategories;
    Button goToYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        goToCategories = findViewById(R.id.goToCategories);
        goToYear = findViewById(R.id.goToYear);

        goToCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCategoriesIntent = new Intent(getApplicationContext(),CategoriesSearch.class);
                startActivity(goToCategoriesIntent);
                customType(HomeScreen.this,"right-to-left");
            }
        });

        goToYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToYearIntent = new Intent(getApplicationContext(),YearSearch.class);
                startActivity(goToYearIntent);
                customType(HomeScreen.this,"left-to-right");
            }
        });

    }
}
