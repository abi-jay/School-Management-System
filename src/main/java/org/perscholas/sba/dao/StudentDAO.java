/**
 *
 * * Filename: StudentDAO.java
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
import org.perscholas.sba.entitymodels.Student;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements StudentDAOInterface{
    /**
     * getAllStudents();
     * getStudentByEmail();
     * validateStudent();
     * registerStudentToCourse();
     * getStudentCourses();
     */
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;
    private Student student;

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
        student = (Student) query.getSingleResult();
        closeConnection();
        return student;
    }

    @Override
    public Boolean validateStudent(String sEmail, String sPassword) {
        initializeConnection();
        Student currentStudent = getStudentByEmail(sEmail);
        if (currentStudent != null & currentStudent.getsPassword().equals(sPassword)) {
                closeConnection();
                return true;
        }
        closeConnection();
        return false;
    }

    @Override
    public void registerStudentToCourse(String sEmail, Course sCourse) {

        Student currentStudent = getStudentByEmail(sEmail);
        Course newCourse = new Course();
        List<Course> updatedCourses = getStudentCourses(sEmail);
        List<Integer> list = new ArrayList<>();
        for (Course cr : updatedCourses) {
            System.out.println(cr.getcId());
            list.add(cr.getcId());
        }
        if (list.contains(sCourse.getcId())) {
            System.out.println("You have already registered in that course!.........\nLogging out, Goodbye!..........");
        }
        else {
            initializeConnection();
            newCourse = session.find(Course.class, sCourse.getcId());
            updatedCourses.add(newCourse);
            currentStudent.setsCourses(updatedCourses);
            session.merge(currentStudent);
            session.getTransaction().commit();
            System.out.println("You have successfully registered!.........\nLogging out, Goodbye!..........");
            closeConnection();
        }

    }

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        initializeConnection();
        Student currentStudent = getStudentByEmail(sEmail);
        List<Course> allCourses = currentStudent.getsCourses();
        closeConnection();
        return allCourses;
    }
}
