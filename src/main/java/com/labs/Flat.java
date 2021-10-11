package com.labs;

public class Flat {
    private int countPeople; // count of people on this flat

    public Flat(int countPeople) {
        this.countPeople = countPeople;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    public int getCountPeople() {
        return countPeople;
    }
}
