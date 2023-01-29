/**
 *
 * * Filename: CourseService.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.perscholas.sba.dao.CourseDAO;
import org.perscholas.sba.dao.CourseDAOInterface;
import org.perscholas.sba.entitymodels.Course;

import javax.persistence.TypedQuery;
import java.util.List;


public class CourseService implements CourseServiceInterface {
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    private Course course;

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
     * getAllCourses();
     * call the respective DAO layer
     */
    @Override
    public List<Course> getAllCourses() {
        CourseDAOInterface courseDAO = new CourseDAO();
        return courseDAO.getAllCourses();
    }

    /**
     *
     * * Establish a connections
     * * Query the results of select * from Student where cid = cid
     * * return the Course object
     * * close the connection
     *
     */
    @Override
    public Course getCourseByCId(int cId) {
        initializeConnection();
        String hql = "FROM Course c where c.cId = :cId";
        TypedQuery query = session.createQuery(hql, Course.class);
        query.setParameter("cId",cId);
        course = (Course) query.getSingleResult();
        closeConnection();
        return course;
    }
    /**
     *
     * * Establish a connections
     * * Save or Update the Course object to the database
     * * Commit the transaction
     * * close the connection
     *
     */
    @Override
    public void saveCourse(Course course) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(course);
        transaction.commit();
        factory.close();
        session.close();
    }
}
