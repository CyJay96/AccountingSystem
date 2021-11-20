package com.accounting.service;

import com.accounting.builder.FloorBuilder;
import com.accounting.model.Apartment;
import com.accounting.model.Floor;

import java.util.ArrayList;
import java.util.List;

public class FloorService {

    public void addFloor(List<Floor> floors, int countFlatsOnFloor) {
        ApartmentService apartmentService = new ApartmentService();

        List<Apartment> apartments = new ArrayList<>();
        for (int i = 0; i < countFlatsOnFloor; ++i) {
            apartmentService.addApartment(floors, apartments);
        }

        Floor floor = new FloorBuilder()
                .withId(floors.size() + 1)
                .withApartments(apartments)
                .build();

        floors.add(floor);
    }

    public void removeFloor(List<Floor> floors, int id) {
        floors.remove(id);
        updateFloorsId(floors);
    }

    public int getCountPeople(Floor floor) {
        int countPeople = 0;

        for (Apartment apartment : floor.getApartments()) {
            countPeople += apartment.getCountPeople();
        }

        return countPeople;
    }

    public boolean compare(Floor floor1, Floor floor2) {
        return floor1.equals(floor2);
    }

    public void updateFloorsId(List<Floor> floors) {
        for (int i = 0; i < floors.size(); ++i) {
            floors.get(i).setId(i + 1);
        }
    }

}
