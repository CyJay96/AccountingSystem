package com.accounting.service;

public class FloorService {

    public FloorService() {
    }

//    public void addFloor(List<Floor> floors, int countFlatsOnFloor) {
//        ApartmentService apartmentService = new ApartmentService();
//
//        List<Apartment> apartments = new ArrayList<>();
//        for (int i = 0; i < countFlatsOnFloor; ++i) {
//            apartmentService.addApartment(floors, apartments);
//        }
//
//        Floor floor = Floor.builder()
//                .id(floors.size() + 1)
//                .apartments(apartments)
//                .build();
//
//        floors.add(floor);
//    }
//
//    public int getCountPeople(List<Floor> floors, int id) {
//        int countPeople = 0;
//
//        for (Apartment apartment : floors.get(id).getApartments()) {
//            countPeople += apartment.getCountPeople();
//        }
//
//        return countPeople;
//    }
//
//    public boolean compare(List<Floor> floors, int firstFloorId, int secondFloorId) {
//        return floors.get(firstFloorId).equals(floors.get(secondFloorId));
//    }

}
