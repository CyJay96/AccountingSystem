package com.accounting.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "floors")
//@Builder(toBuilder = true)
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apartment> apartments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    public Floor() {
    }

//    public Floor(int id, List<Apartment> apartments, House house) {
//        this.id = id;
//        this.apartments = apartments;
//        this.house = house;
//    }

    public int getId() {
        return id;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return id == floor.id && Objects.equals(apartments, floor.apartments) && Objects.equals(house, floor.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apartments, house);
    }

    @Override
    public String toString() {
        return "Floor " + id + ": count of apartments = " + apartments.size();
    }

    public void addApartment(Apartment apartment) {
        apartment.setFloor(this);
        apartments.add(apartment);
    }

    public void removeApartment(Apartment apartment) {
        apartments.remove(apartment);
    }

}
