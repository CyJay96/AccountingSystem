package com.labs;

public class House {
    private int countFloors; // count of floors in this house
    private int countFlatsOnOneFloor; // count flats on a floor
    private double flatArea; // area of a flat

    private double houseArea; // area of house = area of a floor
    private int countAllFlats; // count of all flats in this house
    private int countPeople; // count of people in this house

    private Floor[] floors; // floors in this house

    public House(int countFloors, int countFlatsOnOneFloor, double flatArea) {
        this.countFloors = countFloors;
        this.countFlatsOnOneFloor = countFlatsOnOneFloor;
        this.flatArea = flatArea;

        floors = new Floor[countFloors];
        for (int i = 0; i < countFloors; ++i) {
            floors[i] = new Floor(countFlatsOnOneFloor);
        }

        houseArea = flatArea * countFlatsOnOneFloor;
        countAllFlats = countFlatsOnOneFloor * countFloors;
        for (int i = 0; i < countFloors; ++i) {
            countPeople += floors[i].getCountPeople();
        }
    }

    public void setCountFloors(int countFloors) {
        this.countFloors = countFloors;
        countAllFlats = countFlatsOnOneFloor * countFloors;
        for (int i = 0; i < countFloors; ++i) {
            countPeople += floors[i].getCountPeople();
        }
    }

    public int getCountFloors() {
        return countFloors;
    }

    public void setCountFlatsOnOneFloor(int countFlatsOnOneFloor) {
        this.countFlatsOnOneFloor = countFlatsOnOneFloor;
        houseArea = flatArea * countFlatsOnOneFloor;
        countAllFlats = countFlatsOnOneFloor * countFloors;
        for (int i = 0; i < countFloors; ++i) {
            countPeople += floors[i].getCountPeople();
        }
    }

    public int getCountFlatsOnOneFloor() {
        return countFlatsOnOneFloor;
    }

    public int getCountAllFlats() {
        return countAllFlats;
    }

    public void setFlatArea(double flatArea) {
        this.flatArea = flatArea;
        houseArea = flatArea * countFlatsOnOneFloor;
    }

    public double getFlatArea() {
        return flatArea;
    }

    @Override
    public String toString() {
        return "House {" +
                "countFloors = " + countFloors +
                ", countFlatsOnOneFloor = " + countFlatsOnOneFloor +
                ", flatArea = " + flatArea +
                '}';
    }
}
