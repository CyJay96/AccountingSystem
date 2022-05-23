package com.accounting.service;

import com.accounting.model.Floor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FloorServiceTest {

    private Floor expectedFloor;

    private final int countApartments = 4;
    private final int countPeopleInFlat = 3;
    private final int expectedCountPeople = countApartments * countPeopleInFlat;

    @BeforeEach
    void init() {
        expectedFloor = new Floor();
        for (int i = 0; i < countApartments; ++i) {
            expectedFloor.addApartment(ApartmentService.getInstance().createApartment());
        }
    }

    @Test
    void createFloor() {
        Floor actualFloor = FloorService.getInstance().createFloor(countApartments);
        for (int i = 0; i < countApartments; ++i) {
            actualFloor.getApartments().get(i).setCountPeople(expectedFloor.getApartments().get(i).getCountPeople());
            actualFloor.getApartments().get(i).setCountRooms(expectedFloor.getApartments().get(i).getCountRooms());
            actualFloor.getApartments().get(i).setArea(expectedFloor.getApartments().get(i).getArea());
        }
        Assertions.assertEquals(expectedFloor, actualFloor);
    }

    @Test
    void cloneFloor() {
        Floor actualFloor = FloorService.getInstance().cloneFloor(expectedFloor);
        Assertions.assertEquals(expectedFloor, actualFloor);
    }

    @Test
    void cloneFloorWithoutPeople() {
        Floor actualFloor = FloorService.getInstance().cloneFloorWithoutPeople(expectedFloor);
        for (int i = 0; i < countApartments; ++i) {
            actualFloor.getApartments().get(i).setCountPeople(expectedFloor.getApartments().get(i).getCountPeople());
        }
        Assertions.assertEquals(expectedFloor, actualFloor);
    }

    @Test
    void getCountPeople() {
        Floor actualFloor = FloorService.getInstance().cloneFloorWithoutPeople(expectedFloor);
        for (int i = 0; i < countApartments; ++i) {
            actualFloor.getApartments().get(i).setCountPeople(countPeopleInFlat);
        }
        int actualCountPeople = FloorService.getInstance().getCountPeople(actualFloor);
        Assertions.assertEquals(expectedCountPeople, actualCountPeople);
    }

    @Test
    void compare() {
        boolean actualCompare = FloorService.getInstance().compare(expectedFloor, expectedFloor);
        Assertions.assertTrue(actualCompare);
    }

}
