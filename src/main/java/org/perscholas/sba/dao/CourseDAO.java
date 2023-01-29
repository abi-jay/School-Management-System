/**
 *
 * * Filename: CourseDAO.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.perscholas.sba.entitymodels.Course;

import java.util.List;

public class CourseDAO implements CourseDAOInterface {
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    public void initializeConnection(){
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }
    public void closeConnection(){
        session.close();
        factory.close();
    }
    @Override
    public List<Course> getAllCourses() {
        initializeConnection();
        Query query=session.createQuery("from Course");
        List<Course> results = query.getResultList();
        closeConnection();
        return results;
    }


}
