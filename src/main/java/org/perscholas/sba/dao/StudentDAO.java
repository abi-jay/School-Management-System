package org.perscholas.sba.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.perscholas.sba.entitymodels.Course;
import org.perscholas.sba.entitymodels.Student;

import javax.persistence.TypedQuery;
import java.util.List;

public class StudentDAO implements StudentDAOInterface {
    /**
     * getAllStudents();
     * getStudentByEmail();
     * validateStudent();
     * registerStudentToCourse();
     * getStudentCourses();
     */
    public SessionFactory factory;
    public Session session;
    public Transaction transaction;

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
    public List<Student> getAllStudents() {
        initializeConnection();
        Query query=session.createQuery("from Student");
        List<Student> results = query.getResultList();
        closeConnection();
        return results;
    }

    @Override
    public Student getStudentByEmail(String sEmail) {
        initializeConnection();
        String hql = "FROM Student s where s.sEmail =:sEmail";
        TypedQuery query = session.createQuery(hql, Student.class);
        query.setParameter("sEmail",sEmail);
        Student student = (Student) query.getSingleResult();
        closeConnection();
        return student;
    }

    @Override
    public Boolean validateStudent(String sEmail, String sPassword) {
        return null;
    }

    @Override
    public void registerStudentToCourse(String sEmail, int cId) {

    }

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        return null;
    }
}
