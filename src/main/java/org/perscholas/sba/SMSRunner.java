package org.perscholas.sba;

import org.perscholas.sba.controller.TableConnection;
import org.perscholas.sba.entitymodels.Student;
import org.perscholas.sba.service.StudentService;
import org.perscholas.sba.service.StudentServiceInterface;
import org.perscholas.sba.utils.JdbcConfigurator;

import java.io.FileNotFoundException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class SMSRunner
{
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        TableConnection tableConnect = new TableConnection();

        System.out.println("\nSchool Management System.......");

        System.out.println("Clean database......");
        tableConnect.dropTables();

        System.out.println("\nCreating Tables.......");
        tableConnect.createTables();

        System.out.println("\nInserting Records.......");
        JdbcConfigurator connect = new JdbcConfigurator();
        connect.executeQuery();

        System.out.println("\nRetrieving all Students.......");
        StudentServiceInterface studentService = new StudentService();
        for(Student student:studentService.getAllStudents()){
           System.out.println(student.getsName());
        }

        System.out.println("\nStudent Lookup by Email.......");
        System.out.println(studentService.getStudentByEmail("sbowden1@yellowbook.com").getsName());



    }
}
