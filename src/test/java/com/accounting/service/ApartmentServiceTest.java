package com.accounting.service;

import com.accounting.builder.ApartmentBuilder;
import com.accounting.model.Apartment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ApartmentServiceTest {

    private List<Apartment> apartments;

    @BeforeEach
    void init() {
        apartments = new ArrayList<>();

        Apartment apartment = new ApartmentBuilder()
                .withId(0)
                .withCountPeople(3)
                .withCountRooms(4)
                .withArea(65)
                .build();
        apartments.add(apartment);
    }

    @AfterEach
    void teardown() {
        apartments.clear();
    }

    @Test
    void compare() {
        ApartmentService apartmentService = new ApartmentService();

        boolean expectedCompare = true;
        boolean actualCompare = apartmentService.compare(apartments, 0, 0);

        Assertions.assertEquals(expectedCompare, actualCompare);
    }
}