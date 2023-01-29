/**
 *
 * * Filename: StudentDAOInterface.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba.dao;

import org.perscholas.sba.entitymodels.Course;
import org.perscholas.sba.entitymodels.Student;

import java.util.List;

public interface StudentDAOInterface {
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
     void registerStudentToCourse(String sEmail, Course sCourse);
     List<Course> getStudentCourses(String sEmail);


}
