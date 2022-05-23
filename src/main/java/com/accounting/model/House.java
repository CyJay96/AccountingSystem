package com.accounting.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "houses")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "count_flats_on_floor")
    private int countFlatsOnFloor;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Floor> floors = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        House house = (House) o;
        return countFlatsOnFloor == house.countFlatsOnFloor && Objects.equals(floors, house.floors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countFlatsOnFloor, floors);
    }

    @Override
    public String toString() {
        return "House " + id + ": count of floors = " + floors.size() + ", count of apartments = " +
                countFlatsOnFloor * floors.size();
    }

    public void addFloor(Floor floor) {
        floor.setHouse(this);
        floors.add(floor);
    }

    public void removeFloor(Floor floor) {
        floors.remove(floor);
    }

}
