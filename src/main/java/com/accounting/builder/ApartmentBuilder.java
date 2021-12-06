package com.accounting.builder;

import com.accounting.model.Apartment;
import com.accounting.model.Floor;

public class ApartmentBuilder {

    private final Apartment apartment;

    public ApartmentBuilder() {
        apartment = new Apartment();
    }

    public ApartmentBuilder countPeople(int countPeople) {
        apartment.setCountPeople(countPeople);
        return this;
    }

    public ApartmentBuilder countRooms(int countRooms) {
        apartment.setCountRooms(countRooms);
        return this;
    }

    public ApartmentBuilder area(double area) {
        apartment.setArea(area);
        return this;
    }

    public ApartmentBuilder floor(Floor floor) {
        apartment.setFloor(floor);
        return this;
    }

    public Apartment build() {
        return apartment;
    }

}
