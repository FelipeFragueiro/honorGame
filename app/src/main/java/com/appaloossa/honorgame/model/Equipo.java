package com.appaloossa.honorgame.model;

import java.util.List;

/**
 * Created by Ines on 28/3/2018.
 */

public class Equipo {
    private Integer lost;
    //puntos?

    private Integer score;
    private Integer rating;
    private String place;
    private String placeid;
    private Integer tied;
    private Integer won;
    private String name;
    private String id;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    /* private List<Player> players;*/

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }
/* public Equipo(String userid, Integer lost, Integer score, Integer tied, Integer won, String name, String ubicacion, List<Player> players, Integer rating) {
        this.lost = lost;
        this.score = score;
        this.tied = tied;
        this.won = won;
        this.name = name;
        this.Ubicacion = ubicacion;
        this.players = players;
        this.rating = rating;
        this.userid = userid;
    }*/


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


    public Integer getLost() {
        return lost;
    }

    public void setLost(Integer lost) {
        this.lost = lost;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTied() {
        return tied;
    }

    public void setTied(Integer tied) {
        this.tied = tied;
    }

    public Integer getWon() {
        return won;
    }

    public void setWon(Integer won) {
        this.won = won;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }*/
}
