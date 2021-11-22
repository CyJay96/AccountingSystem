package com.accounting.service;

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

        Floor floor = Floor.builder()
                .id(floors.size() + 1)
                .apartments(apartments)
                .build();

        floors.add(floor);
    }

    public void removeFloor(List<Floor> floors, int id) {
        floors.remove(id);
        updateFloorsId(floors);
    }

    public int getCountPeople(List<Floor> floors, int id) {
        int countPeople = 0;

        for (Apartment apartment : floors.get(id).getApartments()) {
            countPeople += apartment.getCountPeople();
        }

        return countPeople;
    }

    public boolean compare(List<Floor> floors, int firstFloorId, int secondFloorId) {
        return floors.get(firstFloorId).equals(floors.get(secondFloorId));
    }

    public void updateFloorsId(List<Floor> floors) {
        for (int i = 0; i < floors.size(); ++i) {
            floors.get(i).setId(i + 1);
        }
    }

}
