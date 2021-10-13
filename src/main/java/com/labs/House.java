package com.labs;

import java.util.ArrayList;

public class House {
    private int countFlatsOnOneFloor; // count flats on a floor
    private double flatArea; // area of a flat

    private double houseArea; // area of house = area of a floor
    private int countAllFlats; // count of all flats in this house
    private int countPeople; // count of people in this house

    ArrayList<Floor> floors; // floors in this house

    public House(int countFloors, int countFlatsOnOneFloor, double flatArea) {
        this.countFlatsOnOneFloor = countFlatsOnOneFloor;
        this.flatArea = flatArea;

        floors = new ArrayList<>();
        for (int i = 0; i < countFloors; ++i) {
            floors.add(new Floor(countFlatsOnOneFloor));
        }

        houseArea = flatArea * countFlatsOnOneFloor;
        countAllFlats = countFlatsOnOneFloor * countFloors;
        countPeople = 0;
        for (int i = 0; i < countFloors; ++i) {
            countPeople += floors.get(i).getCountPeople();
        }
    }

    public void setCountFloors(int countFloors) {
        countAllFlats = countFlatsOnOneFloor * countFloors;

        if (floors.size() <= countFloors) {
            for (int i = floors.size(); i < countFloors; ++i) {
                floors.add(new Floor(countFlatsOnOneFloor));
            }
        } else {
            for (int i = floors.size() - 1; i >= countFloors; --i) {
                floors.remove(i);
            }
        }

        countPeople = 0;
        for (int i = 0; i < floors.size(); ++i) {
            countPeople += floors.get(i).getCountPeople();
        }
    }

    public int getCountFloors() { // get count of floors
        return floors.size();
    }

    public void setCountFlatsOnOneFloor(int countFlatsOnOneFloor) {
        this.countFlatsOnOneFloor = countFlatsOnOneFloor;
        houseArea = flatArea * countFlatsOnOneFloor;
        countAllFlats = countFlatsOnOneFloor * floors.size();

        for (int i = 0; i < floors.size(); ++i) {
            floors.get(i).setCountFlats(countFlatsOnOneFloor);
        }

        countPeople = 0;
        for (int i = 0; i < floors.size(); ++i) {
            countPeople += floors.get(i).getCountPeople();
        }
    }

    public int getCountFlatsOnOneFloor() {
        return countFlatsOnOneFloor;
    }

    public void setFlatArea(double flatArea) {
        this.flatArea = flatArea;
        houseArea = flatArea * countFlatsOnOneFloor;
    }

    public double getFlatArea() {
        return flatArea;
    }

    public void setHouseArea(double houseArea) {
        this.houseArea = houseArea;
        flatArea = houseArea / countFlatsOnOneFloor;
    }

    public double getHouseArea() { // get area of the house
        return houseArea;
    }

    public int getCountAllFlats() {
        return countAllFlats;
    }

    public int getCountPeople() { // get count of people
        return countPeople;
    }

    @Override
    public String toString() {
        return "Count of floors = " + floors.size() +
                ", Count flats on a floor = " + countFlatsOnOneFloor +
                ", Area of a flat = " + flatArea;
    }

    public void equals(House house) {
        ///
    }
}
