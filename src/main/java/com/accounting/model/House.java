package com.accounting.model;


import lombok.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class House implements Externalizable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int countFlatsOnFloor;
    private List<Floor> floors;

    @Override
    public String toString() {
        return "House " + id + ": count of floors = " + floors.size() + ", count of apartments = " +
                countFlatsOnFloor * floors.size();
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
