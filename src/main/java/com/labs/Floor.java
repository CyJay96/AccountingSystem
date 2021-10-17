package com.labs;

import java.util.ArrayList;

public class Floor {
    ArrayList<Flat> flats; // flats on this floor

    private int countPeople; // count of people on a floor
    private int countPeopleInOneFlat; // count of people in a flat
    private int floorNumber;

    public Floor(int countFlats, int floorNumber) {
        this.floorNumber = floorNumber;
        countPeopleInOneFlat = (int)(Math.random() * 2) + 2; // [2; 4]

        flats = new ArrayList<>();
        for (int i = 0; i < countFlats; ++i) {
            int flatNumber = (floorNumber - 1) * countFlats + i + 1;
            flats.add(new Flat(countPeopleInOneFlat, flatNumber));
        }

        countPeople = 0;
        for (int i = 0; i < countFlats; ++i) {
            countPeople += flats.get(i).getCountPeople();
        }
    }

    public void setCountFlats(int countFlats) {
        if (flats.size() <= countFlats) {
            for (int i = flats.size(); i < countFlats; ++i) {
                flats.add(new Flat(countPeopleInOneFlat, i + 1));
            }
        } else {
            for (int i = flats.size() - 1; i >= countFlats; --i) {
                flats.remove(i);
            }
        }

        countPeople = 0;
        for (int i = 0; i < countFlats; ++i) {
            countPeople += flats.get(i).getCountPeople();
        }
    }

    public int getCountFlats() {
        return flats.size();
    }

    public int getCountPeople() {
        return countPeople;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
