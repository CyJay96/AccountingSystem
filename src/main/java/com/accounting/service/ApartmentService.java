package com.accounting.service;

import com.accounting.builder.ApartmentBuilder;
import com.accounting.model.Apartment;

public class ApartmentService {

    private static volatile ApartmentService instance;

    private ApartmentService() {
    }

    public static ApartmentService getInstance() {
        ApartmentService localInstance = instance;
        if (instance == null) {
            synchronized (ApartmentService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ApartmentService();
                }
            }
        }
        return localInstance;
    }

    public Apartment createApartment() {
        int countPeople = (int) (Math.random() * 2) + 2; // [2; 4]
        int countRooms = (int) (Math.random() * 3) + 1; // [1; 4]
        double area = Math.random() * 50 + 30; // [30; 80]
        area = Math.ceil(area * 100) / 100; // rounding to two characters (cause 100)

        return new ApartmentBuilder()
                .countPeople(countPeople)
                .countRooms(countRooms)
                .area(area)
                .build();
    }

    public Apartment cloneApartment(Apartment apartment) {
        return new ApartmentBuilder()
                .countPeople(apartment.getCountPeople())
                .countRooms(apartment.getCountRooms())
                .area(apartment.getArea())
                .build();
    }

    public boolean compare(Apartment firstApartment, Apartment secondApartment) {
        return firstApartment.equals(secondApartment);
    }

}
