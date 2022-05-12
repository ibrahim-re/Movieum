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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ibrahimr.movieum.ApiInterface;
import com.example.ibrahimr.movieum.Model.MovieCredits;
import com.example.ibrahimr.movieum.Model.MovieResponse;
import com.example.ibrahimr.movieum.Model.Movies;
import com.example.ibrahimr.movieum.R;
import com.example.ibrahimr.movieum.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class CategoriesSearch extends AppCompatActivity implements View.OnClickListener {

    private List<Integer> genres = new ArrayList<>();
    Button readyForCategories;
    private ImageButton backButton;
    private Movies movie = new Movies();
    private MovieCredits movieCredits = new MovieCredits();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_search);
        
        //Ready Button to make a suggestion
        readyForCategories = findViewById(R.id.readyForCategories);
        readyForCategories.setOnClickListener(this);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        backButton = findViewById(R.id.back_button_category);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

    @Override
    public void finish() {
        super.finish();
        customType(CategoriesSearch.this,"left-to-right");
    }


    //A method to create movie genres from checkboxes
    public void createGenres(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        Integer tag = Integer.valueOf((String) view.getTag());
        if(checked){
            if(genres.size() < 3){
                genres.add(tag);
            }else {
                ((CheckBox) view).setChecked(false);
                Toast.makeText(getApplicationContext(),"You can't choose more than 3 categories",Toast.LENGTH_SHORT).show();
            }
        }else{
            genres.remove(tag);
        }
        Log.e("genres",genres.toString());
    }

    
    @Override
    public void onClick(View v) {
        suggestMovie();
    }


    private void suggestMovie() {

        if(genres.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please choose categories",Toast.LENGTH_SHORT).show();
            return;
        }

        //FirstCall is for get number of pages and choose a random page number
        final ApiInterface apiInterface = RetrofitBuilder.getRetrofitInstance(ApiInterface.BASE_URL).create(ApiInterface.class);
        Call<MovieResponse> FirstCall = apiInterface.getMovieResponseList(
                ApiInterface.API_KEY,
                ApiInterface.PAGE,
                removeExtraChars(genres),
                7
        );
        FirstCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    Log.e("request", String.valueOf(call.request()));
                    MovieResponse movieResponse = response.body();
                    int totalPages;
                    assert movieResponse != null;
                    totalPages = movieResponse.getTotal_pages();
                    Log.e("total pages", String.valueOf(totalPages));
                    Random random = new Random();
                    int randomPage;
                    while (true){
                        randomPage = random.nextInt(totalPages);
                        if(randomPage !=0) break;
                    }
                    Log.e("random page", String.valueOf(randomPage));

                    //SecondCall is for get random movie of 20 movies in the previous random page
                    ApiInterface apiInterface1 = RetrofitBuilder.getRetrofitInstance(ApiInterface.BASE_URL).create(ApiInterface.class);
                    Call<MovieResponse> SecondCall = apiInterface1.getMovieResponseList(
                            ApiInterface.API_KEY,
                            randomPage,
                            removeExtraChars(genres),
                            7
                    );
                    SecondCall.enqueue(new Callback<MovieResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                            if(response.isSuccessful()){
                                MovieResponse movieResponse = response.body();
                                assert movieResponse != null;
                                List<Movies> listOfMovies = movieResponse.getResults();
                                Log.e("total pages", String.valueOf(movieResponse.getTotal_pages()));
                                Log.e("total results", String.valueOf(movieResponse.getTotal_results()));
                                for (Movies movies : listOfMovies){
                                    Log.e("movie title",movies.getTitle());
                                }
                                Random random = new Random();
                                int randomIndex = random.nextInt(listOfMovies.size());
                                movie = listOfMovies.get(randomIndex);
                                Log.e("random title",movie.getTitle());
                                int id = movie.getId();
                                Log.e("chosen movie id", String.valueOf(id));

                                //Third Call for get movie credits (cast & crew)
                                ApiInterface apiInterface2 = RetrofitBuilder.getRetrofitInstance(ApiInterface.BASE_URL).create(ApiInterface.class);
                                Call<MovieCredits> thirdCall = apiInterface2.getMovieCredits(id,ApiInterface.API_KEY);
                                thirdCall.enqueue(new Callback<MovieCredits>() {
                                    @Override
                                    public void onResponse(@NonNull Call<MovieCredits> call, @NonNull Response<MovieCredits> response) {

                                        if(response.isSuccessful()){
                                            movieCredits = response.body();
                                            goToResultActivity();
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Response Error",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<MovieCredits> call, @NonNull Throwable t) {
                                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                                    }
                                });


                            }else{
                                Toast.makeText(getApplicationContext(),"Response Error",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    });

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
        Intent goToCategoriesResult = new Intent(getApplicationContext(),CategoriesResult.class);
        goToCategoriesResult.putExtra("chosenMovie",movie);
        goToCategoriesResult.putExtra("movieCredits",movieCredits);
        startActivity(goToCategoriesResult);
    }


    public String removeExtraChars (List<Integer> genres){
        String string = String.valueOf(genres);
        string = string.replace("[","");
        string = string.replace("]","");
        string = string.replace(" ","");
        return string;
    }
}
