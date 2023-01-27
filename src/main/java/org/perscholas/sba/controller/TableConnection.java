package org.perscholas.sba.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.perscholas.sba.entitymodels.Course;
import org.perscholas.sba.entitymodels.Student;

public class TableConnection {
    public SessionFactory factory;
    public Session session;
    public Transaction transaction;

    public void initialize(){
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    public void close(){
        factory.close();
        session.close();
    }
    public  void createTables(){
        initialize();
        Student student = new Student();
        Course course = new Course();
        transaction.commit();
        System.out.println("Successfully created table...........");
        close();
    }
}
