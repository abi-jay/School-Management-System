/**
 *
 * * Filename: StudentServiceTest.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.perscholas.sba.entitymodels.Course;
import org.perscholas.sba.entitymodels.Student;

import java.util.List;

class StudentServiceTest {
    /**
     * Method under test: {StudentService#getStudentByEmail(String)}
     *
     * * Create a Student object
     * * Find the student with this email
     * * Test pass if the database retrieval is successful
     *
     */
    @Test
    void testGetStudentByEmail() {
        StudentServiceInterface studentService = new StudentService();
        Student expectedStudent = new Student();
        //given
        expectedStudent.setsName("Merry Moe");
        expectedStudent.setsEmail("merry@moe.com");
        expectedStudent.setsPassword("123");
        studentService.saveStudent(expectedStudent);
        //when
        Student actualStudent = studentService.getStudentByEmail("merry@moe.com");
        //then
        Assertions.assertEquals(expectedStudent.getsName(),actualStudent.getsName());
    }


    /**
     * Method under test: {StudentService#ValidateStudent(String)}
     *
     * * Create a Student object
     * * Find the student with this email
     * * Validate this with the one from database
     * * Test pass if the database retrieval is successful
     *
     */
    @Test
    void testValidateStudent() {
        StudentServiceInterface studentService = new StudentService();
        Student expectedStudent = new Student();
        //given
        expectedStudent.setsName("Merry Moe");
        expectedStudent.setsEmail("merry@moe.com");
        expectedStudent.setsPassword("123");
        studentService.saveStudent(expectedStudent);
        //when
        Student actualStudent = studentService.getStudentByEmail("merry@moe.com");
        //then
        Assertions.assertEquals(expectedStudent.getsEmail(),actualStudent.getsEmail());
        Assertions.assertEquals(expectedStudent.getsPassword(),actualStudent.getsPassword());
    }

    /**
     * Method under test: { StudentService#registerStudentToCourse(String, Course)}
     *
     * * Create a Student object
     * * Create a Course object
     * * Register this student to this Course
     * * Find the list of courses the student is registered in
     * * Test pass if the database retrieval is found in this list
     *
     */
    @Test
    void testRegisterStudentToCourse() {
        CourseServiceInterface courseService = new CourseService();
        StudentServiceInterface studentService = new StudentService();
        Course expectedCourse = new Course();
        Student expectedStudent = new Student();
        //given
        expectedCourse.setcName("Computer Graphics");
        expectedCourse.setcInstructorName("Roger Federer");
        courseService.saveCourse(expectedCourse);
        expectedStudent.setsName("Merry Moe");
        expectedStudent.setsEmail("merry@moe.com");
        expectedStudent.setsPassword("123");
        studentService.saveStudent(expectedStudent);
        //when
        studentService.registerStudentToCourse(expectedStudent.getsEmail(),expectedCourse);
        List<Course> actualCourseList= courseService.getAllCourses();
        //then
        Assertions.assertTrue(actualCourseList.contains(expectedCourse));
    }
}

