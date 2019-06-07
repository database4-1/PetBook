package com.example.petbook;

public class PetProfile {

    private int petID;
    private String userID;
    private String name;
    private String species;
    private String gender;
    private int age;
    private float weight;
    private String speciesOfSpecies;
    private int avgCost;

    public PetProfile(int petID, String userID, String name, String species, String gender, int age, float weight, String speciesOfSpecies, int avgCost) {
        this.petID = petID;
        this.userID = userID;
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.speciesOfSpecies = speciesOfSpecies;
        this.avgCost = avgCost;
    }

    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getSpeciesOfSpecies() {
        return speciesOfSpecies;
    }

    public void setSpeciesOfSpecies(String speciesOfSpecies) {
        this.speciesOfSpecies = speciesOfSpecies;
    }

    public int getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(int avgCost) {
        this.avgCost = avgCost;
    }
}
