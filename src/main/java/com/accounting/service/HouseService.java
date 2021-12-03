package com.accounting.service;

import com.accounting.builder.HouseBuilder;
import com.accounting.dao.HouseDaoImpl;
import com.accounting.model.Apartment;
import com.accounting.model.Floor;
import com.accounting.model.House;

import java.util.List;

public class HouseService {

    private final HouseDaoImpl houseDao = new HouseDaoImpl();

    public HouseService() {
    }

    public House findHouseSQL(int id) {
        return houseDao.findById(id);
    }

    public void saveHouseSQL(House house) {
        houseDao.save(house);
    }

    public void deleteHouseSQL(House house) {
        houseDao.delete(house);
    }

    public void updateHouseSQL(House house) {
        houseDao.update(house);
    }

    public List<House> findAllHousesSQL() {
        return houseDao.findAll();
    }

    public Floor findFloorByIdSQL(int id) {
        return houseDao.findFloorById(id);
    }

    public House createHouse(int countFloors, int countFlatsOnFloor) {
        House house = new HouseBuilder()
                .countFlatsOnFloor(countFlatsOnFloor)
                .build();

        house.addFloor(new FloorService().createFloor(countFlatsOnFloor));
        int indexFirstFloor = 0;
        for (int i = 0; i < countFloors - 1; ++i) {
            house.addFloor(new FloorService().cloneFloor(house.getFloors().get(indexFirstFloor)));
        }

        return house;
    }

    public House findHouse(List<House> houses, int id) {
        for (House house : houses) {
            if (house.getId() == id) {
                return house;
            }
        }
        return null;
    }

    public double getHouseArea(House house) {
        double houseArea = 0;
        int indexFirstFloor = 0;

        for (Apartment apartment : house.getFloors().get(indexFirstFloor).getApartments()) {
            houseArea += apartment.getArea();
        }

        return Math.ceil(houseArea * 100) / 100;
    }

    public int getCountFloors(House house) {
        return house.getFloors().size();
    }

    public int getCountPeople(House house) {
        FloorService floorService = new FloorService();

        int countPeople = 0;

        for (int i = 0; i < house.getFloors().size(); ++i) {
            countPeople += floorService.getCountPeople(house.getFloors().get(i));
        }

        return countPeople;
    }

    public boolean compare(House firstHouse, House secondHouse) {
        return firstHouse.equals(secondHouse);
    }

}
