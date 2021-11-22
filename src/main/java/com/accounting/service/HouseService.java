package com.accounting.service;

import com.accounting.model.Apartment;
import com.accounting.model.Floor;
import com.accounting.model.House;

import java.util.ArrayList;
import java.util.List;

public class HouseService {

    public void addHouse(List<House> houses, int countFloors, int countFlatsOnFloor) {
        FloorService floorService = new FloorService();

        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < countFloors; ++i) {
            floorService.addFloor(floors, countFlatsOnFloor);
        }

        House house = House.builder()
                .id(houses.size() - 1)
                .countFlatsOnFloor(countFlatsOnFloor)
                .floors(floors)
                .build();

        houses.add(house);
    }

    public void removeHouse(List<House> houses, int id) {
        houses.remove(id);
        updateHousesId(houses);
    }

    public String viewAllHouses(List<House> houses) {
        String housesInfo = "";
        for (House house : houses) {
            housesInfo += house + "\n";
        }

        return housesInfo;
    }

    public String viewHouseById(List<House> houses, int id) {
        return houses.get(id).toString();
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

        for (int i = 0; i < houses.get(id).getFloors().size(); ++i) {
            countPeople += floorService.getCountPeople(houses.get(id).getFloors(), i);
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
