/**
 *
 * * Filename: SMSRunner.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba;

import org.perscholas.sba.controller.TableConnection;
import org.perscholas.sba.entitymodels.Course;
import org.perscholas.sba.entitymodels.Student;
import org.perscholas.sba.service.CourseServiceInterface;
import org.perscholas.sba.service.StudentService;
import org.perscholas.sba.service.StudentServiceInterface;
import org.perscholas.sba.utils.JdbcConfigurator;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * main java
 *
 */
public class SMSRunner
{
        private StudentServiceInterface studentService;
        private CourseServiceInterface courseService;
        private TableConnection tableConnect;
        private Student currentStudent;
        private Scanner consoleInput;
        private StringBuilder stringBuilder;

        public SMSRunner(){
            studentService = new StudentService();
            tableConnect = new TableConnection();
            consoleInput = new Scanner(System.in);
            stringBuilder = new StringBuilder();
        }
        public static void main(String[] args) throws SQLException, FileNotFoundException {
            SMSRunner sms = new SMSRunner();
            System.out.println("\nSchool Management System.......");
            sms.cleanUp();
            sms.createTable();
            sms.insertRecordsFromSql();
            sms.retieveAll();
            sms.lookUp();
            sms.testJoin();
            sms.run();

        }
        private void run() {
            // Login or quit
            switch (menu1()) {
                case 1:
                    if (studentLogin()) {
                        registerMenu();
                    }
                    break;
                case 2:
                    System.out.println("Goodbye!");
                    break;

                default:

            }
        }

    private int menu1() {
        stringBuilder.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
        System.out.println(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        return consoleInput.nextInt();
    }

    private boolean studentLogin() {
        boolean returnValue = false;
        System.out.println("Enter your email address: ");
        String email = consoleInput.next();
        System.out.println("Enter your password: ");
        String password = consoleInput.next();

        currentStudent = studentService.getStudentByEmail(email);

        if (currentStudent != null & currentStudent.getsPassword().equals(password)) {
            List<Course> courses = studentService.getStudentCourses(email);
            System.out.println("My Classes :");
            for (Course course : courses) {
                System.out.println(course);
            }
            returnValue = true;
        } else {
            System.out.println("User Validation failed. GoodBye!");
        }
        return returnValue;
    }

    private void registerMenu() {
        stringBuilder.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
        System.out.println(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());

        switch (consoleInput.nextInt()) {
            case 1:
                List<Course> allCourses = courseService.getAllCourses();
                List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
                // to display courses other than the ones user has already registered
                allCourses.removeAll(studentCourses);
                System.out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
                for (Course course : allCourses) {
                    System.out.println(course);
                }
                System.out.println("Enter Course Number: ");
                int number = consoleInput.nextInt();
                Course newCourse = courseService.GetCourseById(number).get(0);

                if (newCourse != null) {
                    studentService.registerStudentToCourse(currentStudent.getStudentEmail(), newCourse);
                    Student temp = studentService.getStudentByEmail(currentStudent.getStudentEmail()).get(0);

                    StudentCourseService scService = new StudentCourseService();
                    List<Course> sCourses = scService.getAllStudentCourses(temp.getStudentEmail());


                    System.out.println("My Classes :");
                    for (Course course : sCourses) {
                        System.out.println(course);
                    }
                }
                break;
            case 2:
            default:
                System.out.println("Goodbye!");
        }
    }
        private void cleanUp() {
                System.out.println("Clean database......");
                tableConnect.dropTables();
        }

        private void createTable() {
            System.out.println("\nCreating Tables.......");
            tableConnect.createTables();
        }

        private void insertRecordsFromSql() throws SQLException, FileNotFoundException {
            System.out.println("\nInserting Records.......");
            JdbcConfigurator connect = new JdbcConfigurator();
            connect.executeQuery();
        }

        private void retieveAll() {
            System.out.println("\nRetrieving all Students.......");
            for(Student student :studentService.getAllStudents()){
                System.out.println(student.getsName());
            }
        }
        private void lookUp() {
            System.out.println("\nStudent Lookup by Email.......");
            System.out.println(studentService.getStudentByEmail("sbowden1@yellowbook.com").getsName());
            }


        private void testJoin() {
            tableConnect.initializeConnection();
            Course c1 = new Course();
            c1.setcId(101);
            c1.setcName("Graphics");
            c1.setcInstructorName("Flare");

            Course c2 = new Course();
            c2.setcId(102);
            c2.setcName("C++");
            c2.setcInstructorName("Merry");

            Course c3 = new Course();
            c3.setcId(103);
            c3.setcName("Java");
            c3.setcInstructorName("Ecstasy");

            Course c4 = new Course();
            c4.setcId(104);
            c4.setcName("Math");
            c4.setcInstructorName("Harmony");

            Course c5 = new Course();
            c5.setcId(105);
            c5.setcName("History");
            c5.setcInstructorName("Worry");


            List<Course> sCourses = new ArrayList();
            sCourses.add(c1);
            sCourses.add(c2);
            sCourses.add(c3);
            sCourses.add(c4);
            sCourses.add(c5);
            tableConnect.session.save(c1);
            tableConnect.session.save(c2);
            tableConnect.session.save(c3);
            tableConnect.session.save(c4);
            tableConnect.session.save(c5);
            Student stud= new Student();
            stud.setsEmail("abi@haha.com");
            stud.setsCourses(sCourses);
            tableConnect.session.save(stud);
            sCourses.remove(2);
            sCourses.remove(3);
            Student stud2= new Student();
            stud2.setsEmail("iba@hoho.com");
            stud2.setsCourses(sCourses);
            tableConnect.session.save(stud2);
            tableConnect.transaction.commit();
        }
}
