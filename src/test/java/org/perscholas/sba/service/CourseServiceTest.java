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

class CourseServiceTest {
    /**
     * Method under test: {CourseService#getCourseByCId(int)}
     */
    @Test
    void testGetCourseByCId() {
        CourseServiceInterface courseService = new CourseService();
        Course expectedCourse = new Course();
        //given
        expectedCourse.setcId(2);
        expectedCourse.setcName("Computer graphics");
        expectedCourse.setcInstructorName("Roger Federer");
        //when
        Course actualCourse = courseService.getCourseByCId(2);
        //then
        Assertions.assertEquals("Mathematics",actualCourse.getcName());
    }
}

