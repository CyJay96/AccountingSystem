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
public class Floor implements Externalizable {

    private static final long serialVersionUID = 1L;

    private int id;
    private List<Apartment> apartments;

    @Override
    public String toString() {
        return "Floor " + id + ": count of apartments = " + apartments.size();
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
