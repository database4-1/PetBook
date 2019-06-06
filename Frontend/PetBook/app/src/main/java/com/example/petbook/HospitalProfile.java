package com.example.petbook;

public class HospitalProfile {

    private int hospitalID;
    private String name;
    private String phone;
    private String addressDo;
    private String addressSi;
    private String addressGu;
    private String addressDetail;
    private int startTime;
    private int endTime;

    public HospitalProfile(int hospitalID, String name, String phone, String addressDo, String addressSi, String addressGu, String addressDetail, int startTime, int endTime) {
        this.hospitalID = hospitalID;
        this.name = name;
        this.phone = phone;
        this.addressDo = addressDo;
        this.addressSi = addressSi;
        this.addressGu = addressGu;
        this.addressDetail = addressDetail;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
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

    public String getAddressDo() {
        return addressDo;
    }

    public void setAddressDo(String addressDo) {
        this.addressDo = addressDo;
    }

    public String getAddressSi() {
        return addressSi;
    }

    public void setAddressSi(String addressSi) {
        this.addressSi = addressSi;
    }

    public String getAddressGu() {
        return addressGu;
    }

    public void setAddressGu(String addressGu) {
        this.addressGu = addressGu;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
