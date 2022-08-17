package com.myapplicationdev.android.mymovies;

import java.io.Serializable;

public class Movies implements Serializable {

    private int id;
    private int year;
    private String title;
    private String genre;
    private String rating;

    public Movies(int id, int year, String title, String genre, String rating){
        this.id = id;
        this.year = year;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
