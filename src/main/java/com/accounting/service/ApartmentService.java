package com.accounting.service;

public class ApartmentService {

    public ApartmentService() {
    }

//    public void addApartment(List<Floor> floors, List<Apartment> apartments) { // (Math.random() * (b - a)) + a; [a; b]
//        int countPeople = (int) (Math.random() * 2) + 2; // [2; 4]
//        int countRooms = 0;
//        double area = 0;
//
//        if (floors.isEmpty()) {
//            countRooms = (int) (Math.random() * 3) + 1; // [1; 4]
//            area = Math.random() * 50 + 30; // [30; 80]
//            area = Math.ceil(area * 100) / 100; // rounding to two characters (cause 100)
//        } else {
//            int indexFirstFloor = 0;
//            countRooms = floors.get(indexFirstFloor).getApartments().get(apartments.size()).getCountRooms();
//            area = floors.get(indexFirstFloor).getApartments().get(apartments.size()).getArea();
//        }
//
//        Apartment apartment = Apartment.builder()
//                .id(apartments.size() + 1)
//                .countPeople(countPeople)
//                .countRooms(countRooms)
//                .area(area)
//                .build();
//
//        apartments.add(apartment);
//    }
//
//    public boolean compare(List<Apartment> apartments, int firstApartmentId, int secondApartmentId) {
//        return apartments.get(firstApartmentId).equals(apartments.get(secondApartmentId));
//    }

}
