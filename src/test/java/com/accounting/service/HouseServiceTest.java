package com.accounting.service;

import com.accounting.builder.ApartmentBuilder;
import com.accounting.builder.FloorBuilder;
import com.accounting.builder.HouseBuilder;
import com.accounting.model.Apartment;
import com.accounting.model.Floor;
import com.accounting.model.House;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HouseServiceTest {

    private List<House> houses;
    private List<Floor> floors;
    private List<Apartment> apartments;

    @BeforeEach
    void init() {
        houses = new ArrayList<>();
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

        House house = new HouseBuilder()
                .withId(0)
                .withCountFlatsOnFloor(4)
                .withFloors(floors)
                .build();
        houses.add(house);
    }

    @AfterEach
    void teardown() {
        houses.clear();
        floors.clear();
        apartments.clear();
    }

    @Test
    void getHouseArea() {
        HouseService houseService = new HouseService();

        double expectedHouseArea = 65;
        double actualHouseArea = houseService.getHouseArea(houses, 0);

        Assertions.assertEquals(expectedHouseArea, actualHouseArea);
    }

    @Test
    void getCountFloors() {
        HouseService houseService = new HouseService();

        int expectedCountFloors = 1;
        int actualCountFloors = houseService.getCountFloors(houses, 0);

        Assertions.assertEquals(expectedCountFloors, actualCountFloors);
    }

    @Test
    void getCountPeople() {
        HouseService houseService = new HouseService();

        int expectedCountPeople = 3;
        int actualCountPeople = houseService.getCountPeople(houses, 0);

        Assertions.assertEquals(expectedCountPeople, actualCountPeople);
    }

    @Test
    void compare() {
        HouseService houseService = new HouseService();

        boolean expectedCompare = true;
        boolean actualCompare = houseService.compare(houses, 0, 0);

        Assertions.assertEquals(expectedCompare, actualCompare);
    }

}