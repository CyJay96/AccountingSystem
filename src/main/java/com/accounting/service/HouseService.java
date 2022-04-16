package com.accounting.service;

import com.accounting.builder.HouseBuilder;
import com.accounting.dao.HouseDao;
import com.accounting.dao.HouseDaoImpl;
import com.accounting.model.Apartment;
import com.accounting.model.Floor;
import com.accounting.model.House;

import java.util.List;

public class HouseService {

    private static volatile HouseService instance;
    private final HouseDao houseDao;

    private HouseService() {
        houseDao = new HouseDaoImpl();
    }

    public static HouseService getInstance() {
        HouseService localInstance = instance;
        if (instance == null) {
            synchronized (ApartmentService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new HouseService();
                }
            }
        }
        return localInstance;
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

    public House findHouseSQL(int id) {
        return houseDao.findById(id);
    }

    public List<House> findAllHousesSQL() {
        return houseDao.findAll();
    }

    public House createHouse(int countFloors, int countFlatsOnFloor) {
        House house = new HouseBuilder()
                .countFlatsOnFloor(countFlatsOnFloor)
                .build();

        house.addFloor(FloorService.getInstance().createFloor(countFlatsOnFloor));
        int indexFirstFloor = 0;
        for (int i = 0; i < countFloors - 1; ++i) {
            house.addFloor(FloorService.getInstance().cloneFloorWithoutPeople(house.getFloors().get(indexFirstFloor)));
        }

        return house;
    }

    public House cloneHouse(House house) {
        House newHouse = new HouseBuilder()
                .countFlatsOnFloor(house.getCountFlatsOnFloor())
                .build();
        for (Floor floor : house.getFloors()) {
            newHouse.addFloor(FloorService.getInstance().cloneFloor(floor));
        }

        return newHouse;
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
        FloorService floorService = FloorService.getInstance();

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
