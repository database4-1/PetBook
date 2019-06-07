package com.example.petbook;

public class AppointmentProfile {
    private String userID;
    private String username;
    private String petname;
    private String hospitalname;
    private String date;
    private int time;

    public AppointmentProfile(String userID, String username, String petname, String hospitalname, String date, int time) {
        this.userID = userID;
        this.username = username;
        this.petname = petname;
        this.hospitalname = hospitalname;
        this.date = date;
        this.time = time;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
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
}
