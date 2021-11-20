package com.accounting.builder;

import com.accounting.model.Floor;
import com.accounting.model.House;

import java.util.List;

public class HouseBuilder {

    private final House house;

    public HouseBuilder() {
        house = new House();
    }

    public HouseBuilder withId(int id) {
        house.setId(id);
        return this;
    }

    public HouseBuilder withCountFlatsOnFloor(int countFlatsOnFloor) {
        house.setCountFlatsOnFloor(countFlatsOnFloor);
        return this;
    }

    public HouseBuilder withFloors(List<Floor> floors) {
        house.setFloors(floors);
        return this;
    }

    public House build() {
        return house;
    }

}
