/**
 *
 * * Filename: JDBCConfigurator.java
 * * 01/27/2023
 * * @author Abhinaya Jayakumar
 *
 */
package org.perscholas.sba.utils;

import org.perscholas.sba.SMSRunner;
import org.perscholas.sba.controller.TableConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * * Establish a connections
 * * Load JDBC Connectivity Driver
 * * Execute the SQL Files
 * * close the connection
 *
 */
public class JdbcConfigurator {
    public static void executeQuery() throws SQLException, FileNotFoundException {
        TableConnection connection = new TableConnection();
        connection.initializeConnection();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded.....");
        }
        catch(Exception e)
        {
            System.out.println("Error : "+e.toString());
            e.printStackTrace();
        }
        executeSqlFiles("populate-course.sql");
        executeSqlFiles("populate-student.sql");
        connection.closeConnection();
    }
    /**
     *
     * * Find the File path and use Scanner to read the lines
     * * Separate lines and create Statements with the delimiter ;
     * * Execute Update each queries
     *
     */private static void executeSqlFiles(String fileName) {
        String path = SMSRunner.class.getClassLoader().getResource(fileName).getPath();
        System.out.println(path);
        String s = new String();
        StringBuffer sb = new StringBuffer();
        try
        {
            File creationStatementFile = new File((new StringBuilder()
                    .append(System.getProperty("user.dir")) // local directory
                    .append("/src/main/resources/")
                    .append(fileName))
                    .toString());
            System.out.println(creationStatementFile.getAbsolutePath());
            Scanner myReader = new Scanner(creationStatementFile);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms", "abi","123");
            Statement stmt = conn.createStatement();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(">> "+data);
                stmt.executeUpdate(data);
            }
        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e);
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println(sb);
        }
    }
}
