package com.accounting.service;

import com.accounting.model.Floor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FloorServiceTest {

    private Floor expectedFloor;

    private final int countApartments = 4;
    private final int countPeopleOnFlat = 3;
    private final int expectedCountPeople = countApartments * countPeopleOnFlat;

    @BeforeEach
    void init() {
        expectedFloor = new Floor();
        for (int i = 0; i < countApartments; ++i) {
            expectedFloor.addApartment(ApartmentService.getApartmentService().createApartment());
        }
    }

    @Test
    void createFloor() {
        Floor actualFloor = FloorService.getFloorService().createFloor(countApartments);
        Assertions.assertEquals(expectedFloor, actualFloor);
    }

    @Test
    void cloneFloor() {
        Floor actualFloor = FloorService.getFloorService().cloneFloor(expectedFloor);
        Assertions.assertEquals(expectedFloor, actualFloor);
    }

    @Test
    void getCountPeople() {
        int actualCountPeople = FloorService.getFloorService().getCountPeople(expectedFloor);
        Assertions.assertEquals(expectedCountPeople, actualCountPeople);
    }

    @Test
    void compare() {
        boolean actualCompare = FloorService.getFloorService().compare(expectedFloor, expectedFloor);
        Assertions.assertTrue(actualCompare);
    }

}
