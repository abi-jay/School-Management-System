/**
 *
 * * Filename: CourseDAOInterface.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba.dao;

import org.perscholas.sba.entitymodels.Course;

import java.util.List;

public interface CourseDAOInterface {
    /**
     * getAllCourses();
     */
    List<Course> getAllCourses();
}
