/**
 *
 * * Filename: TableConnection.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
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

    /**
     *
     * * Set up factory session and transaction
     *
     */
    public void initializeConnection(){
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    /**
     *
     * * Close factory and session
     *
     */
    public void closeConnection(){
        session.close();
        factory.close();
    }
    /**
     *
     * * Establish a connections
     * * Drop the tables if they exist
     * * Catch the exception if the connection fails
     *
     */
    public void dropTables(){
        initializeConnection();
        try {
            Connection conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "abi","123");
            Statement stmt = conn.createStatement();
            stmt.execute("DROP TABLE if EXISTS `Student_Course`;");
            stmt.execute("DROP TABLE if EXISTS `Student`;");
            stmt.execute("DROP TABLE if EXISTS `Course`;");
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    /**
     *
     * * Establish a connections
     * * Call two table constructors
     * * Commit the transaction and close the connection
     *
     */
    public  void createTables(){
        initializeConnection();
        Student student = new Student();
        Course course = new Course();
        transaction.commit();
        System.out.println("Successfully created table...........");
        closeConnection();
    }
}
