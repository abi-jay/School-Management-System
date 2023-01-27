package org.perscholas.sba.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.perscholas.sba.entitymodels.Course;
import org.perscholas.sba.entitymodels.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TableConnection {
    public SessionFactory factory;
    public Session session;
    public Transaction transaction;

    public void connect(){

    }
    public void initialize(){
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    public void close(){
        factory.close();
        session.close();
    }
    public void dropTables(){
        initialize();
        try {
            Connection conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "abi","123");
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE if EXISTS `Student_Course`;");
            stmt.execute("DROP TABLE if EXISTS `Student`;");
            stmt.execute("DROP TABLE if EXISTS `Course`;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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
