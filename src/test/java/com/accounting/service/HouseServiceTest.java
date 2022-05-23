package com.accounting.service;

import com.accounting.model.Apartment;
import com.accounting.model.House;
import com.accounting.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HouseServiceTest {

    private Session session;

    private House expectedHouse;

    private final int countFlatsOnFloor = 4;
    private final int expectedCountFloors = 5;
    private int expectedCountPeople;
    private double expectedHouseArea;

    @BeforeEach
    void init() {
        expectedHouse = new House()
                .toBuilder()
                .countFlatsOnFloor(countFlatsOnFloor)
                .build();
        expectedHouse.addFloor(FloorService.getInstance().createFloor(countFlatsOnFloor));
        int indexFirstFloor = 0;
        for (int i = 0; i < expectedCountFloors - 1; ++i) {
            expectedHouse.addFloor(FloorService.getInstance().cloneFloorWithoutPeople(expectedHouse.getFloors().get(indexFirstFloor)));
        }

        for (int i = 0; i < expectedHouse.getFloors().size(); ++i) {
            expectedCountPeople += FloorService.getInstance().getCountPeople(expectedHouse.getFloors().get(i));
        }

        for (Apartment apartment : expectedHouse.getFloors().get(indexFirstFloor).getApartments()) {
            expectedHouseArea += apartment.getArea();
        }
        expectedHouseArea = Math.ceil(expectedHouseArea * 100) / 100;
    }

    @BeforeEach
    void openSession() {
        session = HibernateSessionFactory.getSessionFactory().openSession();
    }

    @AfterEach
    void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    @Test
    void saveHouseSQL() {
        HouseService.getInstance().saveHouseSQL(expectedHouse);
        House actualHouse = session.find(House.class, expectedHouse.getId());
        Assertions.assertNotNull(actualHouse);
    }

    @Test
    void deleteHouseSQL() {
        HouseService.getInstance().saveHouseSQL(expectedHouse);
        int id = expectedHouse.getId();
        HouseService.getInstance().deleteHouseSQL(expectedHouse);
        House actualHouse = session.find(House.class, id);
        Assertions.assertNull(actualHouse);
    }

    @Test
    void updateHouseSQL() {
        House updatedHouse = HouseService.getInstance().cloneHouse(expectedHouse);
        HouseService.getInstance().saveHouseSQL(updatedHouse);

        int indexFirstFloor = 0;
        updatedHouse.addFloor(FloorService.getInstance().cloneFloor(updatedHouse.getFloors().get(indexFirstFloor)));
        HouseService.getInstance().updateHouseSQL(updatedHouse);

        House actualHouse = session.find(House.class, updatedHouse.getId());
        Assertions.assertNotNull(actualHouse);
    }

    @Test
    void findHouseSQL() {
        HouseService.getInstance().saveHouseSQL(expectedHouse);
        House actualHouse = HouseService.getInstance().findHouseSQL(expectedHouse.getId());
        Assertions.assertNotNull(actualHouse);
    }

    @Test
    void findAllHousesSQL() {
        House newHouse = HouseService.getInstance().cloneHouse(expectedHouse);

        List<House> expectedHouseList = new ArrayList<>();
        expectedHouseList.add(expectedHouse);
        expectedHouseList.add(newHouse);

        HouseService.getInstance().saveHouseSQL(expectedHouse);
        HouseService.getInstance().saveHouseSQL(newHouse);

        List<House> actualHouseList = HouseService.getInstance().findAllHousesSQL();

        Assertions.assertFalse(actualHouseList.isEmpty());
    }

    @Test
    void createHouse() {
        House actualHouse = HouseService.getInstance().createHouse(expectedCountFloors, countFlatsOnFloor);
        for (int i = 0; i < actualHouse.getFloors().size(); ++i) {
            for (int j = 0; j < actualHouse.getFloors().get(i).getApartments().size(); ++j) {
                actualHouse.getFloors().get(i).getApartments().get(j).
                        setCountPeople(expectedHouse.getFloors().get(i).getApartments().get(j).getCountPeople());
                actualHouse.getFloors().get(i).getApartments().get(j).
                        setCountRooms(expectedHouse.getFloors().get(i).getApartments().get(j).getCountRooms());
                actualHouse.getFloors().get(i).getApartments().get(j).
                        setArea(expectedHouse.getFloors().get(i).getApartments().get(j).getArea());
            }
        }
        Assertions.assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void cloneHouse() {
        House actualHouse = HouseService.getInstance().cloneHouse(expectedHouse);
        Assertions.assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void findHouse() {
        List<House> houses = new ArrayList<>();
        houses.add(expectedHouse);

        House actualHouse = HouseService.getInstance().findHouse(houses, expectedHouse.getId());

        Assertions.assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void getHouseArea() {
        House actualHouse = HouseService.getInstance().createHouse(expectedCountFloors, countFlatsOnFloor);
        for (int i = 0; i < actualHouse.getFloors().size(); ++i) {
            for (int j = 0; j < actualHouse.getFloors().get(i).getApartments().size(); ++j) {
                actualHouse.getFloors().get(i).getApartments().get(j).
                        setArea(expectedHouse.getFloors().get(i).getApartments().get(j).getArea());
            }
        }
        double actualHouseArea = HouseService.getInstance().getHouseArea(actualHouse);
        Assertions.assertEquals(expectedHouseArea, actualHouseArea);
    }

    @Test
    void getCountFloors() {
        int actualCountFloors = HouseService.getInstance().getCountFloors(expectedHouse);
        Assertions.assertEquals(expectedCountFloors, actualCountFloors);
    }

    @Test
    void getCountPeople() {
        House actualHouse = HouseService.getInstance().createHouse(expectedCountFloors, countFlatsOnFloor);
        for (int i = 0; i < actualHouse.getFloors().size(); ++i) {
            for (int j = 0; j < actualHouse.getFloors().get(i).getApartments().size(); ++j) {
                actualHouse.getFloors().get(i).getApartments().get(j).
                        setCountPeople(expectedHouse.getFloors().get(i).getApartments().get(j).getCountPeople());
            }
        }
        int actualCountPeople = HouseService.getInstance().getCountPeople(actualHouse);
        Assertions.assertEquals(expectedCountPeople, actualCountPeople);
    }

    @Test
    void compare() {
        boolean actualCompare = HouseService.getInstance().compare(expectedHouse, expectedHouse);
        Assertions.assertTrue(actualCompare);
    }

}
