package org.perscholas.sba.service;

import org.perscholas.sba.entitymodels.Course;
import org.perscholas.sba.entitymodels.Student;

import java.util.List;

public interface StudentServiceInterface {
    /**
     * getAllStudents();
     * getStudentByEmail();
     * validateStudent();
     * registerStudentToCourse();
     * getStudentCourses();
     */
    List<Student> getAllStudents();
    Student getStudentByEmail(String sEmail);
    Boolean validateStudent(String sEmail, String sPassword);
    void registerStudentToCourse(String sEmail, int cId);
    List<Course> getStudentCourses(String sEmail);
}
