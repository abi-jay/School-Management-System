/**
 *
 * * Filename: CourseServiceTest.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.perscholas.sba.entitymodels.Course;
import org.perscholas.sba.entitymodels.Student;

class CourseServiceTest {
    /**
     * Method under test: {CourseService#getCourseByCId(int)}
     *
     * * Create a Student object
     * * Create a Course object
     * * Register this student to this Course
     * * Find the course with this cId
     * * Test pass if the database retrieval is successful
     *
     */
    @Test
    void testGetCourseByCId() {
        CourseServiceInterface courseService = new CourseService();
        StudentServiceInterface studentService = new StudentService();
        Course expectedCourse = new Course();
        Student expectedStudent = new Student();
        //given
        expectedStudent.setsName("Merry Moe");
        expectedStudent.setsEmail("merry@moe.com");
        expectedStudent.setsPassword("123");
        studentService.saveStudent(expectedStudent);
        expectedCourse.setcName("Mathematics");
        expectedCourse.setcInstructorName("Roger Federer");
        courseService.saveCourse(expectedCourse);
        studentService.registerStudentToCourse("merry@moe.com",expectedCourse);
        //when
        Course actualCourse = courseService.getCourseByCId(expectedCourse.getcId());
        //then
        Assertions.assertEquals(expectedCourse.getcName(),actualCourse.getcName());
    }
}

