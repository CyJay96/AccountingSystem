package com.accounting.service;

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

        Apartment apartment = Apartment.builder()
                .id(0)
                .countPeople(3)
                .countRooms(4)
                .area(65)
                .build();
        apartments.add(apartment);

        Floor floor = Floor.builder()
                .id(0)
                .apartments(apartments)
                .build();
        floors.add(floor);

        House house = House.builder()
                .id(0)
                .countFlatsOnFloor(4)
                .floors(floors)
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
