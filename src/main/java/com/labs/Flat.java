package com.labs;

public class Flat {
    private int countPeople; // count of people on this flat
    private int flatNumber; // number of this flat

    public Flat(int countPeople, int flatNumber) {
        this.countPeople = countPeople;
        this.flatNumber = flatNumber;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    public int getCountPeople() {
        return countPeople;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public int getFlatNumber() {
        return flatNumber;
    }

    @Override
    public String toString() {
        return "Flat " + flatNumber + ": count of people = " + countPeople;
    }
}
