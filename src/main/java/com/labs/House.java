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
            floors.add(new Floor(countFlatsOnOneFloor, i + 1));
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
                floors.add(new Floor(countFlatsOnOneFloor, i + 1));
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

    public void equalsHouses(House house) {
        if (this == house) {
            System.out.println("It's the same house");
            return;
        }

        if (countFlatsOnOneFloor == house.getCountFlatsOnOneFloor() &&
        flatArea == house.flatArea &&
        houseArea == house.houseArea &&
        countAllFlats == house.countAllFlats &&
        countPeople == house.countPeople) {
            System.out.println("These houses are equal in parameters");
        } else {
            System.out.println("These houses are not equal in parameters");
        }
    }

    public void equalsFlats(int flatNumber1, House house, int flatNumber2) {
        if (this == house && flatNumber1 == flatNumber2) {
            System.out.println("It's the same flat");
            return;
        }

        for (int i = 0; i < floors.size(); ++i) {
            for (int j = 0; j < floors.get(i).flats.size(); ++j) {
                if (floors.get(i).flats.get(j).getFlatNumber() == flatNumber1) {
                    for (int k = 0; k < floors.size(); ++k) {
                        for (int m = 0; m < floors.get(k).flats.size(); ++m) {
                            if (floors.get(k).flats.get(m).getFlatNumber() == flatNumber2) {
                                if (floors.get(i).flats.get(j).getCountPeople() == floors.get(k).flats.get(m).getCountPeople()) {
                                    System.out.println("These flats are equal in parameters");
                                } else {
                                    System.out.println("These flats are not equal in parameters");
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
