package com.accounting.dao;

import com.accounting.model.Floor;
import com.accounting.model.House;
import com.accounting.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HouseDaoImpl implements HouseDao {

    public HouseDaoImpl() {
    }

    public House findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        House house = session.get(House.class, id);
        session.close();
        return house;
    }

    public void save(House house) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(house);
        transaction.commit();
        session.close();
    }

    public void update(House house) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(house);
        transaction.commit();
        session.close();
    }

    public void delete(House house) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(house);
        transaction.commit();
        session.close();
    }

    public Floor findFloorById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Floor floor = session.get(Floor.class, id);
        session.close();
        return floor;
    }

    public List<House> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<House> houses = (List<House>) session.createQuery("FROM User").list();
        session.close();
        return houses;
    }

}
