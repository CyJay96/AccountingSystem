package com.accounting.service;

import com.accounting.model.Apartment;
import com.accounting.model.Floor;

public class FloorService {

    private static volatile FloorService instance;

    private FloorService() {
    }

    public static FloorService getInstance() {
        FloorService localInstance = instance;
        if (instance == null) {
            synchronized (ApartmentService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new FloorService();
                }
            }
        }
        return localInstance;
    }

    public Floor createFloor(int countApartments) {
        Floor floor = new Floor();
        for (int i = 0; i < countApartments; ++i) {
            floor.addApartment(ApartmentService.getInstance().createApartment());
        }

        return floor;
    }

    public Floor cloneFloor(Floor floor) {
        Floor newFloor = new Floor();
        for (Apartment apartment : floor.getApartments()) {
            newFloor.addApartment(ApartmentService.getInstance().cloneApartment(apartment));
        }

        return newFloor;
    }

    public Floor cloneFloorWithoutPeople(Floor floor) {
        Floor newFloor = new Floor();
        for (Apartment apartment : floor.getApartments()) {
            newFloor.addApartment(ApartmentService.getInstance().cloneApartment(apartment));
            int countPeople = (int) (Math.random() * 4) + 1; // [1; 4]
            newFloor.getApartments().get(newFloor.getApartments().size() - 1).setCountPeople(countPeople);
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
