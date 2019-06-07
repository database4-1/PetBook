package com.example.petbook;

public class HospitalStatics {
    private String species;
    private int count;

    public HospitalStatics(String species, int count) {
        this.species = species;
        this.count = count;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
