package com.appaloossa.honorgame.model;

import java.util.List;

/**
 * Created by Ines on 28/3/2018.
 */

public class Desafio {
    private int numPos;
    private int contador;
    private List<String> id;
    private String ownerName;
    private String created;
    private String receiverName;
    private String date;
    private String time1;
    private String time2;
    private String time3;
    private String place;
    private String owner;
    private String receiver;
    private Integer state;
    private Equipo unEquipo;

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getNumPos() {
        return numPos;
    }

    public void setNumPos(int numPos) {
        this.numPos = numPos;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public String getTime1() {
        return time1;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Equipo getUnEquipo() {
        return unEquipo;
    }

    public void setUnEquipo(Equipo unEquipo) {
        this.unEquipo = unEquipo;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
