package com.accounting.service;

import com.accounting.model.Apartment;
import com.accounting.model.Floor;

public class FloorService {

    private static FloorService floorService;

    private FloorService() {
    }

    public static synchronized FloorService getFloorService() {
        if (floorService == null) {
            floorService = new FloorService();
        }
        return floorService;
    }

    public Floor createFloor(int countApartments) {
        Floor floor = new Floor();
        for (int i = 0; i < countApartments; ++i) {
            floor.addApartment(ApartmentService.getApartmentService().createApartment());
        }

        return floor;
    }

    public Floor cloneFloor(Floor floor) {
        Floor newFloor = new Floor();
        for (Apartment apartment : floor.getApartments()) {
            newFloor.addApartment(ApartmentService.getApartmentService().cloneApartment(apartment));
        }

        return newFloor;
    }

    public int getCountPeople(Floor floor) {
        int countPeople = 0;

        for (Apartment apartment : floor.getApartments()) {
            countPeople += apartment.getCountPeople();
        }

        return countPeople;
    }

    public boolean compare(Floor firstFloor, Floor secondFloor) {
        return firstFloor.equals(secondFloor);
    }

}
