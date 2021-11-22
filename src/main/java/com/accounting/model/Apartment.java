package com.accounting.model;


import lombok.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class Apartment implements Externalizable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int countPeople;
    private int countRooms;
    private double area;

    @Override
    public String toString() {
        return "Apartment " + id + ": count of people = " + countPeople +
                ", count of rooms = " + countRooms + ", area = " + area;
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
