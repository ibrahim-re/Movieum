package com.example.ibrahimr.movieum.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IbrahimR on 10/18/2020.
 */

public class MovieCredits implements Serializable {

    private int id;
    private List<Cast> cast;
    private List<Crew> crew;

    public MovieCredits(){

    }

    public MovieCredits(int id, List<Cast> cast, List<Crew> crew) {
        this.id = id;
        this.cast = cast;
        this.crew = crew;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public int getId() {
        return id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }
}
