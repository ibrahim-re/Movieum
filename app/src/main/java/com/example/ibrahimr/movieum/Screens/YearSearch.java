package com.example.ibrahimr.movieum.Screens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ibrahimr.movieum.ApiInterface;
import com.example.ibrahimr.movieum.Model.MovieResponse;
import com.example.ibrahimr.movieum.Model.Movies;
import com.example.ibrahimr.movieum.R;
import com.example.ibrahimr.movieum.RetrofitBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class YearSearch extends AppCompatActivity {

    Spinner spinner;
    Button button;
    String chosenYear;
    List<Movies> movies = new ArrayList<>();
    private ImageButton backButton;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year_search);
        spinner = findViewById(R.id.yearSpinner);
        button = findViewById(R.id.readyForYear);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getRandomMovies();
            }
        });


        backButton = findViewById(R.id.back_button_year);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeNav:
                        Intent goToHome = new Intent(getApplicationContext(), HomeScreen.class);
                        startActivity(goToHome);
                        break;
                    case R.id.aboutUsNav:
                        //Toast.makeText(getApplicationContext(),"About US",Toast.LENGTH_SHORT).show();
                        Intent showAboutUs = new Intent(getApplicationContext(), AboutUs.class);
                        startActivity(showAboutUs);
                        break;
                }
                return true;
            }
        });

    }

    private void getRandomMovies() {

        chosenYear = String.valueOf(spinner.getSelectedItem());
        Log.e("chosen year", chosenYear);

        ApiInterface apiInterface = RetrofitBuilder.getRetrofitInstance(ApiInterface.BASE_URL).create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getMoviesBasedOnYear(ApiInterface.API_KEY, Integer.parseInt(chosenYear),"popularity.desc",7);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    MovieResponse movieResponse = response.body();
                    assert movieResponse != null;

                    movies = movieResponse.getResults();
                    Log.e("movies num", String.valueOf(movies.size()));

                    goToResultActivity();

                }else{
                    Toast.makeText(getApplicationContext(),"Response Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToResultActivity() {

        Intent goToYearResult = new Intent(getApplicationContext(),YearResult.class);
        goToYearResult.putExtra("movies", (Serializable) movies);
        startActivity(goToYearResult);
    }

    @Override
    public void finish() {
        super.finish();
        customType(YearSearch.this,"right-to-left");
    }

}
