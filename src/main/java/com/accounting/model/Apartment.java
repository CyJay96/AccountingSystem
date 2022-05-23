package com.accounting.model;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "apartments")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "count_people")
    private int countPeople;

    @Column(name = "count_rooms")
    private int countRooms;

    @Column(name = "area")
    private double area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Apartment apartment = (Apartment) o;
        return countPeople == apartment.countPeople && countRooms == apartment.countRooms && Double.compare(apartment.area, area) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countPeople, countRooms, area);
    }

    @Override
    public String toString() {
        return "Apartment " + id + ": count of people = " + countPeople +
                ", count of rooms = " + countRooms + ", area = " + area;
    }

}
