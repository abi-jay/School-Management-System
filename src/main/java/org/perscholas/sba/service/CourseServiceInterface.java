/**
 *
 * * Filename: CourseServiceInterface.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba.service;

import org.perscholas.sba.entitymodels.Course;

import java.util.List;

public interface CourseServiceInterface {
    /**
     * getAllCourses();
     */
    List<Course> getAllCourses();
    Course getCourseByCId(int cId);
    void saveCourse(Course course);
}
