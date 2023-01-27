package org.perscholas.sba.service;

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
