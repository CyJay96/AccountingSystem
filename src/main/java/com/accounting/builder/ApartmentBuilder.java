package com.accounting.builder;

import com.accounting.model.Apartment;

public class ApartmentBuilder {

    private final Apartment apartment;

    public ApartmentBuilder() {
        apartment = new Apartment();
    }

    public ApartmentBuilder withId(int id) {
        apartment.setId(id);
        return this;
    }

    public ApartmentBuilder withCountPeople(int countPeople) {
        apartment.setCountPeople(countPeople);
        return this;
    }

    public ApartmentBuilder withCountRooms(int countRooms) {
        apartment.setCountRooms(countRooms);
        return this;
    }

    public ApartmentBuilder withArea(double area) {
        apartment.setArea(area);
        return this;
    }

    public Apartment build() {
        return apartment;
    }

}
