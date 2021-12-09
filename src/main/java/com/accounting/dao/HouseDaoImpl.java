package com.accounting.dao;

import com.accounting.model.House;
import com.accounting.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HouseDaoImpl implements HouseDao {

    @Override
    public House findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        House house = session.get(House.class, id);
        session.close();
        return house;
    }

    @Override
    public void save(House house) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(house);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(House house) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(house);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(House house) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(house);
        transaction.commit();
        session.close();
    }

    @Override
    public List<House> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<House> houses = (List<House>) session.createQuery("FROM House").list();
        session.close();
        return houses;
    }

}
