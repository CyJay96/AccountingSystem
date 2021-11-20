package com.accounting.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.Objects;

public class House implements Externalizable {

    private int id;
    private int countFlatsOnFloor;
    private List<Floor> floors;

    public House() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountFlatsOnFloor() {
        return countFlatsOnFloor;
    }

    public void setCountFlatsOnFloor(int countFlatsOnFloor) {
        this.countFlatsOnFloor = countFlatsOnFloor;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public boolean equals(Object HouseObject) {
        if (this == HouseObject) {
            return true;
        }
        if (HouseObject == null || getClass() != HouseObject.getClass()) {
            return false;
        }
        House house = (House) HouseObject;

        return id == house.id && countFlatsOnFloor == house.countFlatsOnFloor && Objects.equals(floors, house.floors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countFlatsOnFloor, floors);
    }

    @Override
    public String toString() {
        return "House " + id + ": count of floors = " + floors.size() + ", floors:" + '\n' + floors;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(countFlatsOnFloor);
        out.writeObject(floors);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (int) in.readObject();
        countFlatsOnFloor = (int) in.readObject();
        floors = (List<Floor>) in.readObject();
    }

}
