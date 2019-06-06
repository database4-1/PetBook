package com.example.petbook;

public class LoginData {

    private String userID;
    private String pw;
    private String name;
    private String phone;
    private String gender;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public static void setInstance(LoginData instance) {
        LoginData.instance = instance;
    }

    private static LoginData instance = null;

    public static synchronized LoginData getInstance(){
        if(null==instance){
            instance = new LoginData();
        }
        return instance;
    }

}
