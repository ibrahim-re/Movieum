package com.example.ibrahimr.movieum.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IbrahimR on 10/18/2020.
 */

public class Movies implements Serializable {

    private double popularity;
    private int id;
    private boolean video;
    private int vote_count;
    private double vote_average;
    private String title;
    private String release_date;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private boolean adult;


    public Movies() {

    }

    public Movies(double popularity, int id, boolean video, int vote_count, double vote_average, String title, String release_date, String original_language, String original_title, List<Integer> genre_ids, String overview, String poster_path, String backdrop_path, boolean adult) {
        this.popularity = popularity;
        this.id = id;
        this.video = video;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.title = title;
        this.release_date = release_date;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.adult = adult;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public int getVote_count() {
        return vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }
}
