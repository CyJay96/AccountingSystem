package com.accounting.service;

import com.accounting.builder.HouseBuilder;
import com.accounting.model.Apartment;
import com.accounting.model.Floor;
import com.accounting.model.House;
import com.accounting.validation.InputValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseService {

    public void addHouse(List<House> houses) {
        Scanner in = new Scanner(System.in);
        InputValidation inputValidation = new InputValidation();

        int minCountFloorsOrFlats = 1;

        FloorService floorService = new FloorService();

        System.out.print("Enter the number of floors: ");
        int countFloors = inputValidation.inputIntValue(minCountFloorsOrFlats);

        System.out.print("Enter the number of apartments on a floor: ");
        int countFlatsOnFloor = inputValidation.inputIntValue(minCountFloorsOrFlats);

        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < countFloors; ++i) {
            floorService.addFloor(floors, countFlatsOnFloor);
        }

        House house = new HouseBuilder()
                .withId(houses.size() + 1)
                .withCountFlatsOnFloor(countFlatsOnFloor)
                .withFloors(floors)
                .build();

        houses.add(house);
    }

    public void removeHouse(List<House> houses, int id) {
        houses.remove(id);
        updateHousesId(houses);
    }

    public void viewAllHouses(List<House> houses) {
        System.out.println(houses);
    }

    public void viewHouseById(List<House> houses, int id) {
        System.out.println(houses.get(id));
    }

    public double getHouseArea(List<House> houses, int id) {
        double houseArea = 0;
        int indexFirstFloor = 0;

        for (Apartment apartment : houses.get(id).getFloors().get(indexFirstFloor).getApartments()) {
            houseArea += apartment.getArea();
        }

        houseArea = Math.ceil(houseArea * 100) / 100;

        return houseArea;
    }

    public int getCountFloors(List<House> houses, int id) {
        return houses.get(id).getFloors().size();
    }

    public int getCountPeople(List<House> houses, int id) {
        FloorService floorService = new FloorService();

        int countPeople = 0;

        for (Floor floor : houses.get(id).getFloors()) {
            countPeople += floorService.getCountPeople(floor);
        }

        return countPeople;
    }

    public boolean compare(List<House> houses, int firstHouseId, int secondHouseId) {
        return houses.get(firstHouseId).equals(houses.get(secondHouseId));
    }

    public void updateHousesId(List<House> houses) {
        for (int i = 0; i < houses.size(); ++i) {
            houses.get(i).setId(i + 1);
        }
    }

}
