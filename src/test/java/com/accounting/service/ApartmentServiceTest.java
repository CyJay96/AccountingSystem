package com.accounting.service;

import com.accounting.builder.ApartmentBuilder;
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
        expectedApartment = new ApartmentBuilder()
                .countPeople(countPeople)
                .countRooms(countRooms)
                .area(area)
                .build();
    }

    @Test
    void createApartment() {
        Apartment actualApartment = ApartmentService.getApartmentService().createApartment();
        Assertions.assertEquals(expectedApartment, actualApartment);
    }

    @Test
    void cloneApartment() {
        Apartment actualApartment = ApartmentService.getApartmentService().cloneApartment(expectedApartment);
        Assertions.assertEquals(expectedApartment, actualApartment);
    }

    @Test
    void compare() {
        boolean actualCompare = ApartmentService.getApartmentService().compare(expectedApartment, expectedApartment);
        Assertions.assertTrue(actualCompare);
    }

}
