package com.example.ibrahimr.movieum;

import com.example.ibrahimr.movieum.Model.MovieCredits;
import com.example.ibrahimr.movieum.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by IbrahimR on 10/18/2020.
 */

public interface ApiInterface {

    String BASE_URL = "https://api.themoviedb.org/3/";
    int PAGE = 1;
    String API_KEY = "c05b8485c000dc72be953e71932dd60e";

    @GET("discover/movie")
    Call<MovieResponse> getMovieResponseList(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("with_genres") String withGenres,
            @Query("vote_average.gte") int voteAverage
    );

    @GET("movie/{id}/credits")
    Call<MovieCredits> getMovieCredits(
            @Path("id") int id,
            @Query("api_key") String apiKey
    );

    @GET("discover/movie")
    Call<MovieResponse> getMoviesBasedOnYear(
            @Query("api_key") String apiKey,
            @Query("primary_release_year") int year,
            @Query("sort_by") String sortBy,
            @Query("vote_average.gte") int voteAverage
    );


}
