package com.accounting.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.Objects;

public class Floor implements Externalizable {

    private int id;
    private List<Apartment> apartments;

    public Floor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public boolean equals(Object FloorObject) {
        if (this == FloorObject) {
            return true;
        }
        if (FloorObject == null || getClass() != FloorObject.getClass()) {
            return false;
        }
        Floor floor = (Floor) FloorObject;

        return id == floor.id && Objects.equals(apartments, floor.apartments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apartments);
    }

    @Override
    public String toString() {
        return "Floor " + id + ": count of apartments = " + apartments.size() + ", apartments: " + '\n' + apartments;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(apartments);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (int) in.readObject();
        apartments = (List<Apartment>) in.readObject();
    }

}
