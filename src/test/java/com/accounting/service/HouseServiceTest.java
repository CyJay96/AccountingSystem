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

        expectedHouse.addFloor(FloorService.getInstance().createFloor(countFlatsOnFloor));
        int indexFirstFloor = 0;
        for (int i = 0; i < expectedCountFloors - 1; ++i) {
            expectedHouse.addFloor(FloorService.getInstance().cloneFloor(expectedHouse.getFloors().get(indexFirstFloor)));
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
        double actualHouseArea = HouseService.getInstance().getHouseArea(expectedHouse);
        Assertions.assertEquals(expectedHouseArea, actualHouseArea);
    }

    @Test
    void getCountFloors() {
        int actualCountFloors = HouseService.getInstance().getCountFloors(expectedHouse);
        Assertions.assertEquals(expectedCountFloors, actualCountFloors);
    }

    @Test
    void getCountPeople() {
        int actualCountPeople = HouseService.getInstance().getCountPeople(expectedHouse);
        Assertions.assertEquals(expectedCountPeople, actualCountPeople);
    }

    @Test
    void compare() {
        boolean actualCompare = HouseService.getInstance().compare(expectedHouse, expectedHouse);
        Assertions.assertTrue(actualCompare);
    }

}
