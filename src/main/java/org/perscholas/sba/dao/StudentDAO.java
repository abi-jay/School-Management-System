package org.perscholas.sba.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StudentDAO implements StudentDAOInterface {
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    private void initialization(){
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    private void close(){
        factory.close();
        session.close();
    }

}
