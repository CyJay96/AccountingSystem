package com.labs;

public class House {
    private int countFloors;
    private int countFlatsOnOneFloor;
    private double flatArea;

    private double houseArea;
    private int countAllFlats;
    private int countPeople;

    private Floor[] floors;

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
    }

    public int getCountFloors() {
        return countFloors;
    }

    public void setCountFlatsOnOneFloor(int countFlatsOnOneFloor) {
        this.countFlatsOnOneFloor = countFlatsOnOneFloor;
    }

    public int getCountFlatsOnOneFloor() {
        return countFlatsOnOneFloor;
    }

    public void setCountAllFlats(int countAllFlats) {
        this.countAllFlats = countAllFlats;
    }

    public int getCountAllFlats() {
        return countAllFlats;
    }

    public void setFlatArea(double flatArea) {
        this.flatArea = flatArea;
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
