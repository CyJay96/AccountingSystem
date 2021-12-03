package com.accounting.dao;

import com.accounting.model.Floor;
import com.accounting.model.House;

import java.util.List;

public interface HouseDao {

    House findById(int id);

    void save(House house);

    void update(House house);

    void delete(House house);

    Floor findFloorById(int id);

    List<House> findAll();

}
