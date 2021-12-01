package com.accounting.dao;

import com.accounting.model.Floor;
import com.accounting.model.House;
import com.accounting.util.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HouseDaoImpl implements HouseDao {

    public House findById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(House.class, id);
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
        return HibernateSessionFactory.getSessionFactory().openSession().get(Floor.class, id);
    }

    public List<House> findAll() {
        return (List<House>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From House").list();
    }

}
