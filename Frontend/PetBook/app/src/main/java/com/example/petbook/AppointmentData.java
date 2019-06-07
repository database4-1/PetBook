package com.example.petbook;

import com.google.gson.JsonObject;

import java.util.Date;

public class AppointmentData {

    private String userID;
    private int petID;
    private int hospitalID;
    private String date;
    private int time;
    private String content;

    public AppointmentData(String userID, int petID, int hospitalID, String date, int time, String content) {
        this.userID = userID;
        this.petID = petID;
        this.hospitalID = hospitalID;
        this.date = date;
        this.time = time;
        this.content = content;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
