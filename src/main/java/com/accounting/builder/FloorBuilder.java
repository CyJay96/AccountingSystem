package com.accounting.builder;

import com.accounting.model.Apartment;
import com.accounting.model.Floor;

import java.util.List;

public class FloorBuilder {

    private final Floor floor;

    public FloorBuilder() {
        floor = new Floor();
    }

    public FloorBuilder withId(int id) {
        floor.setId(id);
        return this;
    }

    public FloorBuilder withApartments(List<Apartment> apartments) {
        floor.setApartments(apartments);
        return this;
    }

    public Floor build() {
        return floor;
    }

}
