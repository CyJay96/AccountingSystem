package com.accounting.dao;

import com.accounting.model.Floor;
import com.accounting.model.House;

import java.util.List;

public interface HouseDao {

    public House findById(int id);

    public void save(House house);

    public void update(House house);

    public void delete(House house);

    public Floor findFloorById(int id);

    public List<House> findAll();

}
