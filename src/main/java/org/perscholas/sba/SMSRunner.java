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
