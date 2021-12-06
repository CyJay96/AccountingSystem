package com.accounting.service;

import com.accounting.model.Apartment;
import com.accounting.model.Floor;

public class FloorService {

    public FloorService() {
    }

    public Floor createFloor(int countApartments) {
        Floor floor = new Floor();
        for (int i = 0; i < countApartments; ++i) {
            floor.addApartment(new ApartmentService().createApartment());
        }

        return floor;
    }

    public Floor cloneFloor(Floor floor) {
        Floor newFloor = new Floor();
        for (Apartment apartment : floor.getApartments()) {
            newFloor.addApartment(new ApartmentService().cloneApartment(apartment));
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
