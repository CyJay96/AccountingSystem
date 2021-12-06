package com.accounting.dao;

import com.accounting.model.House;
import com.accounting.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HouseDaoImpl implements HouseDao {

    private final Session session;

    public HouseDaoImpl() {
        session = HibernateSessionFactory.getSessionFactory().openSession();
    }

    public House findById(int id) {
        House house = session.get(House.class, id);
        session.close();
        return house;
    }

    public void save(House house) {
        Transaction transaction = session.beginTransaction();
        session.save(house);
        transaction.commit();
        session.close();
    }

    public void update(House house) {
        Transaction transaction = session.beginTransaction();
        session.update(house);
        transaction.commit();
        session.close();
    }

    public void delete(House house) {
        Transaction transaction = session.beginTransaction();
        session.delete(house);
        transaction.commit();
        session.close();
    }

    public List<House> findAll() {
        List<House> houses = (List<House>) session.createQuery("FROM House").list();
        session.close();
        return houses;
    }

}
