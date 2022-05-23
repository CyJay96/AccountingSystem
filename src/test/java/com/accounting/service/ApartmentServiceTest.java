package com.accounting.service;

import com.accounting.model.Apartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApartmentServiceTest {

    private Apartment expectedApartment;

    private final int countPeople = 3;
    private final int countRooms = 4;
    private final double area = 65.4;

    @BeforeEach
    void init() {
        expectedApartment = new Apartment()
                .toBuilder()
                .countPeople(countPeople)
                .countRooms(countRooms)
                .area(area)
                .build();
    }

    @Test
    void createApartment() {
        Apartment actualApartment = ApartmentService.getInstance().createApartment();
        actualApartment.setCountPeople(countPeople);
        actualApartment.setCountRooms(countRooms);
        actualApartment.setArea(area);
        Assertions.assertEquals(expectedApartment, actualApartment);
    }

    @Test
    void cloneApartment() {
        Apartment actualApartment = ApartmentService.getInstance().cloneApartment(expectedApartment);
        Assertions.assertEquals(expectedApartment, actualApartment);
    }

    @Test
    void compare() {
        boolean actualCompare = ApartmentService.getInstance().compare(expectedApartment, expectedApartment);
        Assertions.assertTrue(actualCompare);
    }

}
