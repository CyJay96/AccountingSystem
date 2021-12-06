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

        expectedHouse.addFloor(new FloorService().createFloor(countFlatsOnFloor));
        int indexFirstFloor = 0;
        for (int i = 0; i < expectedCountFloors - 1; ++i) {
            expectedHouse.addFloor(new FloorService().cloneFloor(expectedHouse.getFloors().get(indexFirstFloor)));
        }
    }

    @BeforeEach
    void openSession() {
        session = HibernateSessionFactory.getSessionFactory().openSession();
    }

    @Test
    void findHouseSQL() {
        new HouseService().saveHouseSQL(expectedHouse);
        House actualHouse = new HouseService().findHouseSQL(expectedHouse.getId());
        Assertions.assertNotNull(actualHouse);
    }

    @Test
    void saveHouseSQL() {
        new HouseService().saveHouseSQL(expectedHouse);
        House actualHouse = session.find(House.class, expectedHouse.getId());
        Assertions.assertNotNull(actualHouse);
    }

    @Test
    void deleteHouseSQL() {
        new HouseService().saveHouseSQL(expectedHouse);
        int id = expectedHouse.getId();
        new HouseService().deleteHouseSQL(expectedHouse);
        House actualHouse = session.find(House.class, id);
        Assertions.assertNull(actualHouse);
    }

    @Test
    void updateHouseSQL() {
        House updatedHouse = new HouseService().cloneHouse(expectedHouse);
        new HouseService().saveHouseSQL(updatedHouse);

        int indexFirstFloor = 0;
        updatedHouse.addFloor(new FloorService().cloneFloor(updatedHouse.getFloors().get(indexFirstFloor)));
        new HouseService().updateHouseSQL(updatedHouse);

        House actualHouse = session.find(House.class, updatedHouse.getId());
        Assertions.assertNotNull(actualHouse);
    }

    @Test
    void findAllHousesSQL() {
        House newHouse = new HouseService().cloneHouse(expectedHouse);

        List<House> expectedHouseList = new ArrayList<>();
        expectedHouseList.add(expectedHouse);
        expectedHouseList.add(newHouse);

        new HouseService().saveHouseSQL(expectedHouse);
        new HouseService().saveHouseSQL(newHouse);

        List<House> actualHouseList = new HouseService().findAllHousesSQL();

        Assertions.assertNotNull(actualHouseList);
    }

    @Test
    void createHouse() {
        House actualHouse = new HouseService().createHouse(expectedCountFloors, countFlatsOnFloor);
        Assertions.assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void cloneHouse() {
        House actualHouse = new HouseService().cloneHouse(expectedHouse);
        Assertions.assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void findHouse() {
        List<House> houses = new ArrayList<>();
        houses.add(expectedHouse);

        House actualHouse = new HouseService().findHouse(houses, expectedHouse.getId());

        Assertions.assertEquals(expectedHouse, actualHouse);
    }

    @Test
    void getHouseArea() {
        double actualHouseArea = new HouseService().getHouseArea(expectedHouse);
        Assertions.assertEquals(expectedHouseArea, actualHouseArea);
    }

    @Test
    void getCountFloors() {
        int actualCountFloors = new HouseService().getCountFloors(expectedHouse);
        Assertions.assertEquals(expectedCountFloors, actualCountFloors);
    }

    @Test
    void getCountPeople() {
        int actualCountPeople = new HouseService().getCountPeople(expectedHouse);
        Assertions.assertEquals(expectedCountPeople, actualCountPeople);
    }

    @Test
    void compare() {
        boolean actualCompare = new HouseService().compare(expectedHouse, expectedHouse);
        Assertions.assertTrue(actualCompare);
    }

    @AfterEach
    void closeSession() {
        if (session != null) {
            session.close();
        }
    }

}
