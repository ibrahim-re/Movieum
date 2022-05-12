package com.example.ibrahimr.movieum.Model;

import java.io.Serializable;

/**
 * Created by IbrahimR on 10/18/2020.
 */

public class Crew implements Serializable {
    private String credit_id;
    private String department;
    private int gender;
    private int id;
    private String job;
    private String name;
    private String profile_path;

    public Crew(){

    }

    public Crew(String credit_id, String department, int gender, int id, String job, String name, String profile_path) {
        this.credit_id = credit_id;
        this.department = department;
        this.gender = gender;
        this.id = id;
        this.job = job;
        this.name = name;
        this.profile_path = profile_path;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public String getDepartment() {
        return department;
    }

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
