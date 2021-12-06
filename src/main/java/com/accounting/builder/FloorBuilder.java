package com.accounting.builder;

import com.accounting.model.Apartment;
import com.accounting.model.Floor;
import com.accounting.model.House;

import java.util.List;

public class FloorBuilder {

    private final Floor floor;

    public FloorBuilder() {
        floor = new Floor();
    }

    public FloorBuilder apartments(List<Apartment> apartments) {
        floor.setApartments(apartments);
        return this;
    }

    public FloorBuilder house(House house) {
        floor.setHouse(house);
        return this;
    }

    public Floor build() {
        return floor;
    }

}
