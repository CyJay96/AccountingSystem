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
            expectedFloor.addApartment(new ApartmentService().createApartment());
        }
    }

    @Test
    void createFloor() {
        Floor actualFloor = new FloorService().createFloor(countApartments);
        Assertions.assertEquals(expectedFloor, actualFloor);
    }

    @Test
    void cloneFloor() {
        Floor actualFloor = new FloorService().cloneFloor(expectedFloor);
        Assertions.assertEquals(expectedFloor, actualFloor);
    }

    @Test
    void getCountPeople() {
        int actualCountPeople = new FloorService().getCountPeople(expectedFloor);
        Assertions.assertEquals(expectedCountPeople, actualCountPeople);
    }

    @Test
    void compare() {
        boolean actualCompare = new FloorService().compare(expectedFloor, expectedFloor);
        Assertions.assertTrue(actualCompare);
    }

}
