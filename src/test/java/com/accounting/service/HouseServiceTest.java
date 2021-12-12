package com.accounting.service;

import com.accounting.builder.HouseBuilder;
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

    private final int expectedCountFloors = 5;
    private final int countFlatsOnFloor = 4;
    private final int countPeopleOnFlat = 3;
    private final int expectedCountPeople = expectedCountFloors * countFlatsOnFloor * countPeopleOnFlat;

    private final double expectedHouseArea = 261.61;

    @BeforeEach
    void init() {
        expectedHouse = new HouseBuilder()
                .countFlatsOnFloor(countFlatsOnFloor)
                .build();

        expectedHouse.addFloor(FloorService.getFloorService().createFloor(countFlatsOnFloor));
        int indexFirstFloor = 0;
        for (int i = 0; i < expectedCountFloors - 1; ++i) {
            expectedHouse.addFloor(FloorService.getFloorService().cloneFloor(expectedHouse.getFloors().get(indexFirstFloor)));
        }
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
        HouseService.getHouseService().saveHouseSQL(expectedHouse);
        House actualHouse = session.find(House.class, expectedHouse.getId());
        Assertions.assertNotNull(actualHouse);
    }

    @Test
    void deleteHouseSQL() {
        HouseService.getHouseService().saveHouseSQL(expectedHouse);
        int id = expectedHouse.getId();
        HouseService.getHouseService().deleteHouseSQL(expectedHouse);
        House actualHouse = session.find(House.class, id);
        Assertions.assertNull(actualHouse);
    }

    @Test
    void updateHouseSQL() {
        House updatedHouse = HouseService.getHouseService().cloneHouse(expectedHouse);
        HouseService.getHouseService().saveHouseSQL(updatedHouse);

        int indexFirstFloor = 0;
        updatedHouse.addFloor(FloorService.getFloorService().cloneFloor(updatedHouse.getFloors().get(indexFirstFloor)));
        HouseService.getHouseService().updateHouseSQL(updatedHouse);

        House actualHouse = session.find(House.class, updatedHouse.getId());
        Assertions.assertNotNull(actualHouse);
    }

    @Test
    void findHouseSQL() {
        HouseService.getHouseService().saveHouseSQL(expectedHouse);
        House actualHouse = HouseService.getHouseService().findHouseSQL(expectedHouse.getId());
        Assertions.assertNotNull(actualHouse);
    }

    @Test
    void findAllHousesSQL() {
        House newHouse = HouseService.getHouseService().cloneHouse(expectedHouse);

        List<House> expectedHouseList = new ArrayList<>();
        expectedHouseList.add(expectedHouse);
        expectedHouseList.add(newHouse);

        HouseService.getHouseService().saveHouseSQL(expectedHouse);
        HouseService.getHouseService().saveHouseSQL(newHouse);

        List<House> actualHouseList = HouseService.getHouseService().findAllHousesSQL();

        Assertions.assertFalse(actualHouseList.isEmpty());
    }

    @Test
    void createHouse() {
        House actualHouse = HouseService.getHouseService().createHouse(expectedCountFloors, countFlatsOnFloor);
        Assertions.assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void cloneHouse() {
        House actualHouse = HouseService.getHouseService().cloneHouse(expectedHouse);
        Assertions.assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void findHouse() {
        List<House> houses = new ArrayList<>();
        houses.add(expectedHouse);

        House actualHouse = HouseService.getHouseService().findHouse(houses, expectedHouse.getId());

        Assertions.assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void getHouseArea() {
        double actualHouseArea = HouseService.getHouseService().getHouseArea(expectedHouse);
        Assertions.assertEquals(expectedHouseArea, actualHouseArea);
    }

    @Test
    void getCountFloors() {
        int actualCountFloors = HouseService.getHouseService().getCountFloors(expectedHouse);
        Assertions.assertEquals(expectedCountFloors, actualCountFloors);
    }

    @Test
    void getCountPeople() {
        int actualCountPeople = HouseService.getHouseService().getCountPeople(expectedHouse);
        Assertions.assertEquals(expectedCountPeople, actualCountPeople);
    }

    @Test
    void compare() {
        boolean actualCompare = HouseService.getHouseService().compare(expectedHouse, expectedHouse);
        Assertions.assertTrue(actualCompare);
    }

}
