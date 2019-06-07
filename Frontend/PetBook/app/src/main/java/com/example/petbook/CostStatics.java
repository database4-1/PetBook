package com.example.petbook;

public class CostStatics {

    private String speciesOfSpecies;
    private int age;
    private int weight;
    private float avgCost;

    public CostStatics(String speciesOfSpecies, int age, int weight, float avgCost) {
        this.speciesOfSpecies = speciesOfSpecies;
        this.age = age;
        this.weight = weight;
        this.avgCost = avgCost;
    }

    public String getSpeciesOfSpecies() {
        return speciesOfSpecies;
    }

    public void setSpeciesOfSpecies(String speciesOfSpecies) {
        this.speciesOfSpecies = speciesOfSpecies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(float avgCost) {
        this.avgCost = avgCost;
    }
}
