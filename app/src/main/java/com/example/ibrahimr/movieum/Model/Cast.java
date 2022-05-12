package com.example.ibrahimr.movieum.Model;

import java.io.Serializable;

/**
 * Created by IbrahimR on 10/18/2020.
 */

public class Cast implements Serializable {
    private int cast_id;
    private String character;
    private String credit_id;
    private int gender;
    private int id;
    private String name;
    private int order;
    private String profile_path;

    public Cast(){

    }

    public Cast(int cast_id, String character, String credit_id, int gender, int id, String name, int order, String profile_path) {
        this.cast_id = cast_id;
        this.character = character;
        this.credit_id = credit_id;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.order = order;
        this.profile_path = profile_path;
    }

    public void setCast_id(int cast_id) {
        this.cast_id = cast_id;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public int getCast_id() {
        return cast_id;
    }

    public String getCharacter() {
        return character;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public String getProfile_path() {
        return profile_path;
    }
}
