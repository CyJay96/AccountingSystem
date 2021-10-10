package com.labs;

public class Floor {
    private int countFlats;

    private Flat[] flats;

    private int countPeople;

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
    }

    public int getCountFlats() {
        return countFlats;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    public int getCountPeople() {
        return countPeople;
    }
}
