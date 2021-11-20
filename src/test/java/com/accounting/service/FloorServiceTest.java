package com.accounting.service;

import com.accounting.builder.ApartmentBuilder;
import com.accounting.builder.FloorBuilder;
import com.accounting.model.Apartment;
import com.accounting.model.Floor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FloorServiceTest {

    private List<Floor> floors;
    private List<Apartment> apartments;

    @BeforeEach
    void init() {
        floors = new ArrayList<>();
        apartments = new ArrayList<>();

        Apartment apartment = new ApartmentBuilder()
                .withId(0)
                .withCountPeople(3)
                .withCountRooms(4)
                .withArea(65)
                .build();
        apartments.add(apartment);

        Floor floor = new FloorBuilder()
                .withId(0)
                .withApartments(apartments)
                .build();
        floors.add(floor);
    }

    @AfterEach
    void teardown() {
        floors.clear();
        apartments.clear();
    }

    @Test
    void getCountPeople() {
        FloorService floorService = new FloorService();

        int expectedCountPeople = 3;
        int actualCountPeople = floorService.getCountPeople(floors, 0);

        Assertions.assertEquals(expectedCountPeople, actualCountPeople);
    }

    @Test
    void compare() {
        FloorService floorService = new FloorService();

        boolean expectedCompare = true;
        boolean actualCompare = floorService.compare(floors, 0, 0);

        Assertions.assertEquals(expectedCompare, actualCompare);
    }

}