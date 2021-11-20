package com.accounting.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public class Apartment implements Externalizable {

    private int id;
    private int countPeople;
    private int countRooms;
    private double area;

    public Apartment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    public int getCountRooms() {
        return countRooms;
    }

    public void setCountRooms(int countRooms) {
        this.countRooms = countRooms;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object ApartmentObject) {
        if (this == ApartmentObject) {
            return true;
        }
        if (ApartmentObject == null || getClass() != ApartmentObject.getClass()) {
            return false;
        }
        Apartment apartment = (Apartment) ApartmentObject;

        return id == apartment.id && countPeople == apartment.countPeople && countRooms == apartment.countRooms
                && Double.compare(apartment.area, area) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countPeople, countRooms, area);
    }

    @Override
    public String toString() {
        return "Apartment " + id + ": count of people = " + countPeople +
                ", count of rooms = " + countRooms + ", area = " + area + '\n';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(countPeople);
        out.writeObject(countRooms);
        out.writeObject(area);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (int) in.readObject();
        countPeople = (int) in.readObject();
        countRooms = (int) in.readObject();
        area = (double) in.readObject();
    }

}
