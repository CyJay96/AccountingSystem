package com.accounting.util;

import com.accounting.model.Apartment;
import com.accounting.model.Floor;
import com.accounting.model.House;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(House.class);
                configuration.addAnnotatedClass(Floor.class);
                configuration.addAnnotatedClass(Apartment.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
