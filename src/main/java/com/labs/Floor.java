package com.labs;

public class Floor {
    private int countFlats; // count of flats on this floor

    private Flat[] flats; // flats on this floor

    private int countPeople; // count of people on this floor

    public Floor(int countFlats) {
        this.countFlats = countFlats;

        flats = new Flat[countFlats];
        for (int i = 0; i < countFlats; ++i) {
            flats[i] = new Flat(3); // builder
        }

        for (int i = 0; i < countFlats; ++i) {
            countPeople += flats[i].getCountPeople();
        }
    }

    public void setCountFlats(int countFlats) {
        this.countFlats = countFlats;
        for (int i = 0; i < countFlats; ++i) {
            countPeople += flats[i].getCountPeople();
        }
    }

    public int getCountFlats() {
        return countFlats;
    }

    public int getCountPeople() {
        return countPeople;
    }
}
