package com.accounting.service;

import com.accounting.builder.ApartmentBuilder;
import com.accounting.model.Apartment;
import com.accounting.model.Floor;

import java.util.List;

public class ApartmentService {

    public void addApartment(List<Floor> floors, List<Apartment> apartments) { // (Math.random() * (b - a)) + a; [a; b]
        int countPeople = (int) (Math.random() * 2) + 2; // [2; 4]
        int countRooms = 0;
        double area = 0;

        if (floors.isEmpty()) {
            countRooms = (int) (Math.random() * 3) + 1; // [1; 4]
            area = Math.random() * 50 + 30; // [30; 80]
            area = Math.ceil(area * 100) / 100; // rounding to two characters (cause 100)
        } else {
            int indexFirstFloor = 0;
            countRooms = floors.get(indexFirstFloor).getApartments().get(apartments.size()).getCountRooms();
            area = floors.get(indexFirstFloor).getApartments().get(apartments.size()).getArea();
        }

        Apartment apartment = new ApartmentBuilder()
                .withId(apartments.size() + 1)
                .withCountPeople(countPeople)
                .withCountRooms(countRooms)
                .withArea(area)
                .build();

        apartments.add(apartment);
    }

    public void removeApartment(List<Apartment> apartments, int id) {
        apartments.remove(id);
        updateApartmentsId(apartments);
    }

    public boolean compare(Apartment apartment1, Apartment apartment2) {
        return apartment1.equals(apartment2);
    }

    public void updateApartmentsId(List<Apartment> apartments) {
        for (int i = 0; i < apartments.size(); ++i) {
            apartments.get(i).setId(i + 1);
        }
    }

}
