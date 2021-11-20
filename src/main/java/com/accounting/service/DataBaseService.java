package com.accounting.service;

import com.accounting.model.House;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class DataBaseService {

    public void writeDataBase(List<House> houses, String dataBaseName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataBaseName))) {
            objectOutputStream.writeObject(houses);
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }
    }

    public void readDataBase(List<House> houses, String dataBaseName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataBaseName))) {
            for (House house : (List<House>) objectInputStream.readObject()) {
                houses.add(house);
            }
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }
    }

}
