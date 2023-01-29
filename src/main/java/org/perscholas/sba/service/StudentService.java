/**
 *
 * * Filename: StudentService.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.perscholas.sba.dao.StudentDAO;
import org.perscholas.sba.dao.StudentDAOInterface;
import org.perscholas.sba.entitymodels.Course;
import org.perscholas.sba.entitymodels.Student;

import java.util.List;

public class StudentService implements StudentServiceInterface {
    /**
     * getAllStudents();
     * getStudentByEmail();
     * validateStudent();
     * registerStudentToCourse();
     * getStudentCourses();
     */
    @Override
    public List<Student> getAllStudents() {
        StudentDAOInterface studentDAO = new StudentDAO();
        return studentDAO.getAllStudents();
    }

    @Override
    public Student getStudentByEmail(String sEmail) {
        StudentDAOInterface studentDAO = new StudentDAO();
        return studentDAO.getStudentByEmail(sEmail);
    }

    @Override
    public Boolean validateStudent(String sEmail, String sPassword) {

        StudentDAOInterface studentDAO = new StudentDAO();
        return studentDAO.validateStudent(sEmail, sPassword);
    }

    @Override
    public void registerStudentToCourse(String sEmail, Course newCourse) {
        StudentDAOInterface studentDAO = new StudentDAO();
        studentDAO.registerStudentToCourse(sEmail,newCourse);
    }

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        StudentDAOInterface studentDAO = new StudentDAO();
        return studentDAO.getStudentCourses(sEmail);
    }
    @Override
    public void saveStudent(Student student) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(student);
        transaction.commit();
        factory.close();
        session.close();
    }
}
