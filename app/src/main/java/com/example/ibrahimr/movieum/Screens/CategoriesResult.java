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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibrahimr.movieum.Model.Cast;
import com.example.ibrahimr.movieum.Adapters.CastAdapter;
import com.example.ibrahimr.movieum.Model.Crew;
import com.example.ibrahimr.movieum.Model.MovieCredits;
import com.example.ibrahimr.movieum.Model.Movies;
import com.example.ibrahimr.movieum.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class CategoriesResult extends AppCompatActivity {

    private final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    private HashMap<Integer,String> genresId = new HashMap<>();
    private ImageButton backButton;

    ImageView moviePoster;
    TextView movieTitle;
    TextView movieCategories;
    TextView movieRate;
    TextView movieAbout;
    TextView movieDirector;
    TextView movieReleaseYear;
    RecyclerView movieCastRecyclerView;
    List<String> castImages = new ArrayList<>();
    List<String> castNames = new ArrayList<>();
    String directorName;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_result);
        setGenresId();

        moviePoster = findViewById(R.id.moviePoster);
        movieTitle = findViewById(R.id.movieTitle);
        movieCategories = findViewById(R.id.categories);
        movieRate = findViewById(R.id.movieRate);
        movieAbout = findViewById(R.id.aboutDisc);
        movieDirector = findViewById(R.id.directorName);
        movieReleaseYear = findViewById(R.id.releasedYear);
        movieCastRecyclerView = findViewById(R.id.castRecyclerView);
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

        Intent intent = getIntent();
        Movies chosenMovie = (Movies) intent.getSerializableExtra("chosenMovie");
        MovieCredits chosenMovieCredits = (MovieCredits) intent.getSerializableExtra("movieCredits");
        Log.e("Result Activity", chosenMovie.getTitle());

        List<Cast> chosenMovieCast = chosenMovieCredits.getCast();
        List<Crew> chosenMovieCrew = chosenMovieCredits.getCrew();

        setMovieCast(chosenMovieCast);
        setDirectorName(chosenMovieCrew);

        if (chosenMovie.getPoster_path() != null) {
            Glide.with(this)
                    .load(BASE_IMAGE_URL + chosenMovie.getPoster_path())
                    .transform(new RoundedCornersTransformation(30, 0))
                    .into(moviePoster);
        }else{
            Glide.with(this)
                    .load(R.drawable.no_poster)
                    .transform(new RoundedCornersTransformation(30, 0))
                    .into(moviePoster);
        }

        movieTitle.setText(chosenMovie.getTitle());
        movieRate.setText(String.valueOf(chosenMovie.getVote_average()));
        movieAbout.setText(chosenMovie.getOverview());
        movieDirector.setText(directorName);

        List<String> categories = new ArrayList<>();
        for (int num : chosenMovie.getGenre_ids()){
            if (num == 37 || num == 10770){continue;}
            categories.add(genresId.get(num));
        }
        String categoriesString = removeExtraChars(String.valueOf(categories));
        Log.e("categories", categoriesString);
        movieCategories.setText(categoriesString);

        String date = chosenMovie.getRelease_date();
        String [] dateParts = date.split("-");
        String year = dateParts[0];
        Log.e("year", year);

        movieReleaseYear.setText("("+year+")");

        for (String s : castImages){
            Log.e("image", s);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        CastAdapter castAdapter = new CastAdapter(this,castImages,castNames);
        movieCastRecyclerView.setLayoutManager(layoutManager);
        movieCastRecyclerView.setAdapter(castAdapter);


        backButton = findViewById(R.id.back_button_category_result);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setGenresId(){

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

    public void setDirectorName(List<Crew> chosenMovieCrew){
        if (chosenMovieCrew != null){
            for (Crew c: chosenMovieCrew) {
                if (c.getJob().toLowerCase().equals("director")){
                    directorName = c.getName();
                    break;
                }else{
                    directorName = "Unknown";
                }
            }
            Log.e("director name",directorName);
        }else{
            Log.e("Fuck","Fuck");
            directorName = "Unknown";
        }
    }

    public void setMovieCast(List<Cast> chosenMovieCast){
        if (chosenMovieCast != null){
            Log.e("ll", String.valueOf(chosenMovieCast.size()));
            for (int i=0 ; i<chosenMovieCast.size(); i++) {
                Log.e("Actor Name", chosenMovieCast.get(i).getName());
                if (chosenMovieCast.get(i).getProfile_path() != null){
                    Log.e("Actor Image", chosenMovieCast.get(i).getProfile_path());
                    castImages.add(BASE_IMAGE_URL + chosenMovieCast.get(i).getProfile_path());
                }else{
                    castImages.add("https://icon-library.com/images/no-image-available-icon/no-image-available-icon-7.jpg");
                }
                castNames.add(chosenMovieCast.get(i).getName());
                if (i == 5){break;}
            }
        }else{
            Log.e("Fuck","Fuck");
        }
    }
}
