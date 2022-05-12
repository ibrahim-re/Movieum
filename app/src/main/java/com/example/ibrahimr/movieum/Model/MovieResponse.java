package com.example.ibrahimr.movieum.Model;

import java.util.List;

/**
 * Created by IbrahimR on 10/18/2020.
 */

public class MovieResponse {
    private int page;
    private int total_results;
    private int total_pages;
    private List<Movies> results;

    public MovieResponse() {
    }

    public MovieResponse(int page, int total_results, int total_pages, List<Movies> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setResults(List<Movies> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Movies> getResults() {
        return results;
    }
}
