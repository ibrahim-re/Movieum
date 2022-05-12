package com.example.ibrahimr.movieum.Screens;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.ibrahimr.movieum.Adapters.MovieAdapter;
import com.example.ibrahimr.movieum.Model.Movies;
import com.example.ibrahimr.movieum.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YearResult extends AppCompatActivity {

    private final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    private HashMap<Integer,String> genresId = new HashMap<>();
    private ImageButton backButton;

    RecyclerView recyclerView;
    List<String> moviesTitles = new ArrayList<>();
    List<String> moviesCategories = new ArrayList<>();
    List<String> movieRates = new ArrayList<>();
    List<String> moviePosters = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.year_result);
        setGenresId();

        recyclerView = findViewById(R.id.moviesRecyclerView);


        Intent intent = getIntent();
        ArrayList<Movies> movies = (ArrayList<Movies>) getIntent().getSerializableExtra("movies");
        Log.e("rrrr", String.valueOf(movies.size()));

        for (Movies m : movies) {
            moviesTitles.add(m.getTitle());
            movieRates.add(String.valueOf(m.getVote_average()));
            moviesCategories.add(getMovieCategories(m));
            if (m.getPoster_path() != null){
                moviePosters.add(BASE_IMAGE_URL + m.getPoster_path());
            }else{
                moviePosters.add("https://icon-library.com/images/no-image-available-icon/no-image-available-icon-7.jpg");
            }
        }

        MovieAdapter movieAdapter = new MovieAdapter(this,moviesTitles,moviesCategories,moviePosters,movieRates,movies);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backButton = findViewById(R.id.back_button_year_result);
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

    private String getMovieCategories(Movies m) {
        List<String> categories = new ArrayList<>();
        for (int num : m.getGenre_ids()){
            if (num == 37 || num == 10770){continue;}
            categories.add(genresId.get(num));
        }
        Log.e("categories", removeExtraChars(String.valueOf(categories)));
        return removeExtraChars(String.valueOf(categories));
    }

    private void setGenresId (){

        genresId.put(28, "Action");
        genresId.put(12, "Adventure");
        genresId.put(16, "Animation");
        genresId.put(80, "Crime");
        genresId.put(35, "Comedy");
        genresId.put(10751, "Family");
        genresId.put(14, "Fantasy");
        genresId.put(36, "History");
        genresId.put(27, "Horror");
        genresId.put(9648, "Mystery");
        genresId.put(10402, "Music");
        genresId.put(10749, "Romance");
        genresId.put(10752, "War");
        genresId.put(99, "Documentary");
        genresId.put(18, "Drama");
        genresId.put(878, "Sci-Fi");
        genresId.put(53, "Thriller");

    }

    private String removeExtraChars (String string){
        string = string.replace("[","");
        string = string.replace("]","");
        return string;
    }


}
