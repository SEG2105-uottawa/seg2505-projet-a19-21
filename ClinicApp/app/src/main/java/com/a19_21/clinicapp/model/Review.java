package com.a19_21.clinicapp.model;

public class Review {


    private int Score;
    private String username;
    private User user;
    private String id;
    private Service service;



    public Review (int Score, User user,Service service){
        this.Score = Score;
        this.user = user;
        this.service = service;

        username = user.getUsername();
    }


    public void setScore(int score){
        this.Score = score;
    }

    public int getScore(){
        return Score;
    }

    public Service getreviewedservice(){

        return service;
    }
    public User getUserReviewing(){
        return user;
    }
}
