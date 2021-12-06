package com.accounting.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "apartments")
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

    public Apartment() {
    }

    public Apartment(int countPeople, int countRooms, double area) {
        this.countPeople = countPeople;
        this.countRooms = countRooms;
        this.area = area;
    }

    public Apartment(int id, int countPeople, int countRooms, double area, Floor floor) {
        this.id = id;
        this.countPeople = countPeople;
        this.countRooms = countRooms;
        this.area = area;
        this.floor = floor;
    }

    public int getId() {
        return id;
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

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

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
